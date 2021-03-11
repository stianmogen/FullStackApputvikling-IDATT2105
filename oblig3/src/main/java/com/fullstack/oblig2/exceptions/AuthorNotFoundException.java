package com.fullstack.oblig2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthorNotFoundException extends ResponseStatusException {


    public AuthorNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

}
