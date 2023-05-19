package it.example.spotify_clone.services;

import it.example.spotify_clone.entities.Album;
import it.example.spotify_clone.entities.Artist;
import it.example.spotify_clone.entities.Section;
import it.example.spotify_clone.entities.Song;
import it.example.spotify_clone.exceptions.ElementNotFoundException;
import it.example.spotify_clone.repositories.AlbumRepository;
import it.example.spotify_clone.repositories.ArtistRepository;
import it.example.spotify_clone.repositories.SectionRepository;
import it.example.spotify_clone.repositories.SongRepository;
import it.example.spotify_clone.repositories.projections.ReducedAlbumInfo;
import it.example.spotify_clone.repositories.projections.ReducedSongInfo;
import it.example.spotify_clone.utilities.FileUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SpotifyService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist addArtist(String artistName) {
        Artist artist = new Artist();
        artist.setName(artistName);

        return artistRepository.save(artist);
    }

    public List<ReducedAlbumInfo> getAllAlbums() {
        return albumRepository.findBy();
    }

    @Transactional
    public Album addAlbum(String albumTitle, MultipartFile albumCover, Long artistID, String description) {
        Artist artist = artistRepository.findById(artistID).
                orElseThrow(() -> new ElementNotFoundException("artist: " + artistID));

        Album album = new Album();
        album.setArtist(artist);
        album.setTitle(albumTitle);
        album.setDescription(description);

        String coverName = artistID + albumTitle;
        album.setCover(coverName);

        FileUtility.getInstance().store(albumCover, coverName, true);
        return albumRepository.save(album);
    }

    public Resource getAlbumCover(Long albumID) {
        Album album = albumRepository.findById(albumID).
                orElseThrow(() -> new ElementNotFoundException("album: " + albumID));

        return FileUtility.getInstance().load(album.getArtist().getId() + album.getTitle(), true);
    }

    public Album getAlbum(Long albumID) {
        return albumRepository.findById(albumID).
                orElseThrow(() -> new ElementNotFoundException("album: " + albumID));
    }

    @Transactional
    public Song addSong(String title, MultipartFile audio, Long albumID, Long artistID) {
        Song song = new Song();
        Album album = albumRepository.findById(albumID).
                orElseThrow(() -> new ElementNotFoundException("album: " + albumID));

        Artist artist = artistRepository.findById(artistID).
                orElseThrow(() -> new ElementNotFoundException("artist: " + artistID));

        song.setTitle(title);
        song.setAlbum(album);
        song.setArtist(artist);

        String audioName = album.getId() + title;
        song.setAudio(audioName);

        FileUtility.getInstance().store(audio, audioName, false);

        return songRepository.save(song);
    }

    public Resource getAudio(Long songID) {
        Song song = songRepository.findById(songID).
                orElseThrow(() -> new ElementNotFoundException("song: " + songID));

        return FileUtility.getInstance().load(song.getAlbum().getId() + song.getTitle(), false);
    }

    public List<ReducedSongInfo> getAlbumSongs(Long albumID) {
        return songRepository.findByAlbum_Id(albumID);
    }

    public Song getSong(Long songID) {
        return songRepository.findById(songID).orElseThrow(() -> new ElementNotFoundException("song: " + songID));
    }

    public Section addSection(String sectionName) {
        Section section = new Section();
        section.setName(sectionName);
        return sectionRepository.save(section);
    }

}
