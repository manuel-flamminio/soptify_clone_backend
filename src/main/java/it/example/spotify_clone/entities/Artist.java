package it.example.spotify_clone.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;


}
