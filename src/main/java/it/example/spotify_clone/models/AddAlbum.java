package it.example.spotify_clone.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddAlbum {

    @NotNull(message = "can't be null")
    @Size(min = 1, max = 20, message = "must be between 1 and 20 char")
    private String title;

    @NotNull
    @Size(min = 2, max = 20)
    private String description;

    @NotNull(message = "is missing")
    private Long artistID;

    @NotNull(message = "is missing")
    private MultipartFile cover;

    private Long sectionID;
}
