package it.example.spotify_clone.repositories;

import it.example.spotify_clone.entities.Album;
import it.example.spotify_clone.repositories.projections.ReducedAlbumInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<ReducedAlbumInfo> findBySection_Id(Long id);
}
