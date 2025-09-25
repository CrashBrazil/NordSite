package com.example.NORD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonalizadaException extends RuntimeException{
    public PersonalizadaException(String Ex) {
        super(Ex);
    }
}
