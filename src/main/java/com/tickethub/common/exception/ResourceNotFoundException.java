package com.tickethub.common.exception;

/** 404: el recurso no existe o el usuario no tiene permiso de verlo (no revelamos cuál de los dos). */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
