package it.example.spotify_clone.repositories;

import it.example.spotify_clone.entities.Song;
import it.example.spotify_clone.repositories.projections.ReducedSongInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<ReducedSongInfo> findByAlbum_Id(Long id);
}
