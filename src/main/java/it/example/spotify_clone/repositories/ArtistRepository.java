package it.example.spotify_clone.repositories;

import it.example.spotify_clone.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
