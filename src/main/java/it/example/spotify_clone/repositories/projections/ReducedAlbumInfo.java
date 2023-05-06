package it.example.spotify_clone.repositories.projections;

/**
 * A Projection for the {@link it.example.spotify_clone.entities.Album} entity
 */
public interface ReducedAlbumInfo {
    Long getId();

    String getTitle();

    String getDescription();

    String getCover();
}