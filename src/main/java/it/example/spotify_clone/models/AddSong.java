package it.example.spotify_clone.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddSong {

    @Size(min = 1, max = 20)
    @NotNull
    private String title;

    @NotNull
    private Long albumID;

    @NotNull
    private Long artistID;

    @NotNull
    private MultipartFile audio;
}
