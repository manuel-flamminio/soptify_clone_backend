package it.example.spotify_clone.utilities;

import it.example.spotify_clone.exceptions.ElementAlreadyExistException;
import it.example.spotify_clone.exceptions.ElementNotFoundException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {
    public static final String PATH_IMAGE = "/home/azeze/Data/Todos/Uni/Spring/spotify_storage";
    private static final String IMAGE_EXTENSION = ".png";
    private static final String AUDIO_EXTENSION = ".mp3";
    private static FileUtility instance;

    private  FileUtility(){
    }

    public static FileUtility getInstance(){
        if (instance == null)
            instance = new FileUtility();
        return  instance;
    }

    private void controlPath(Path root, String path){
        if(!Files.exists(root)){
            try {
                Files.createDirectories(Paths.get(path));
            } catch (IOException e){
                throw new ElementAlreadyExistException("Non è stato possibile inizializzare la directory");
            }
        }
    }

    public void store(MultipartFile multipartFile, String fileName, boolean isImage){
        String extension = isImage ? IMAGE_EXTENSION : AUDIO_EXTENSION;

        try{
            File file = new File(PATH_IMAGE + "/" + fileName.replaceAll(" ","_") + extension);
            multipartFile.transferTo(file);

            if (isImage) {
                Thumbnails.of(file).size(300,300).outputQuality(1).toFile(file);
            }

        }catch (IOException ex){
            throw new ElementAlreadyExistException("Errore nell'inserimento del file");
        }

    }

    public Resource load(String fileName, boolean isImage){
        String extension = isImage ? IMAGE_EXTENSION : AUDIO_EXTENSION;
        String path = PATH_IMAGE + "/" + fileName.replaceAll(" ","_") + extension;

        try {
            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists()){
                return resource;
            }else{
                throw new ElementNotFoundException("File non trovato");
            }

        }catch(MalformedURLException e){
            throw new ElementAlreadyExistException("Malformed Url");
        }
    }

}
