package it.example.spotify_clone.repositories.projections;

import it.example.spotify_clone.entities.Artist;

/**
 * A Projection for the {@link it.example.spotify_clone.entities.Song} entity
 */
public interface ReducedSongInfo {
    Long getId();

    String getTitle();

    String getAudio();

    Artist getArtist();
}