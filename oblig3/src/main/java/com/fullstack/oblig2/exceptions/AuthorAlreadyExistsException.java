package com.fullstack.oblig2.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException() {
    }

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
