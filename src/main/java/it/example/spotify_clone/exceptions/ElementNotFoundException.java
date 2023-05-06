package it.example.spotify_clone.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}