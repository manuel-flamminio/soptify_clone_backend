package it.example.spotify_clone.repositories;

import it.example.spotify_clone.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Long> {
}
