package it.example.spotify_clone.controllers;


import it.example.spotify_clone.entities.Album;
import it.example.spotify_clone.entities.Artist;
import it.example.spotify_clone.entities.Section;
import it.example.spotify_clone.entities.Song;
import it.example.spotify_clone.models.AddAlbum;
import it.example.spotify_clone.models.AddSong;
import it.example.spotify_clone.repositories.SectionRepository;
import it.example.spotify_clone.repositories.projections.ReducedAlbumInfo;
import it.example.spotify_clone.repositories.projections.ReducedSongInfo;
import it.example.spotify_clone.services.SpotifyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/spotify")
public class SpotifyController {

    @Autowired
    private SpotifyService service;
    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(service.getAllArtists());
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> addArtist(@RequestParam("name") String artistName) {
        return ResponseEntity.ok(service.addArtist(artistName));
    }

    @PostMapping(value = "/albums", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Album> addAlbum(
            @Valid @ModelAttribute AddAlbum request
            ) {
        return ResponseEntity.ok(service.addAlbum(request.getTitle(), request.getCover(), request.getArtistID(), request.getDescription()));
    }

    @GetMapping("/albums")
    public ResponseEntity<List<ReducedAlbumInfo>> getAllAlbums() {
        return ResponseEntity.ok(service.getAllAlbums());
    }

    @GetMapping(value = "/albums/{id}/cover", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getAlbumCover(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAlbumCover(id));
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAlbum(id));
    }

    @PostMapping("/songs")
    private ResponseEntity<Song> addSong(
            @Valid @ModelAttribute AddSong request
            ) {
        return ResponseEntity.ok(service.addSong(request.getTitle(), request.getAudio(), request.getAlbumID(), request.getArtistID()));
    }

    @GetMapping(value = "/songs/{id}/audio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    private ResponseEntity<Resource> getAudio(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAudio(id));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSong(id));
    }

    @GetMapping("/albums/{id}/songs")
    public ResponseEntity<List<ReducedSongInfo>> getAlbumSongs(@PathVariable Long id) {
        return  ResponseEntity.ok(service.getAlbumSongs(id));
    }

    @PostMapping("/sections")
    public ResponseEntity<Section> addSection(@RequestParam("name") String sectionName) {
        return ResponseEntity.ok(service.addSection(sectionName));
    }

    @PutMapping("/sections/{id}/albums")
    public ResponseEntity<Album> updateSectionAlbums(@PathVariable(name = "id") Long sectionID, @RequestParam("albumID") Long albumID) {
        return ResponseEntity.ok(service.updateSectionAlbums(sectionID, albumID));
    }

}
