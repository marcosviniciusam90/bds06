package com.devsuperior.movieflix.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource with ID %s not found", id));
    }
}
