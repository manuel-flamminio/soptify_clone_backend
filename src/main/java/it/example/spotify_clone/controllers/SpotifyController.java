package it.example.spotify_clone.controllers;


import it.example.spotify_clone.entities.Album;
import it.example.spotify_clone.entities.Artist;
import it.example.spotify_clone.entities.Song;
import it.example.spotify_clone.models.AddAlbum;
import it.example.spotify_clone.models.AddSong;
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
        return ResponseEntity.ok(service.addAlbum(request.getTitle(), request.getCover(), request.getArtistID()));
    }

    @GetMapping("/albums")
    public ResponseEntity<List<ReducedAlbumInfo>> getAllAlbums() {
        return ResponseEntity.ok(service.getAllAlbums());
    }

}
