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

}
