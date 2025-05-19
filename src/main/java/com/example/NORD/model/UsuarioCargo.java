package com.example.NORD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum UsuarioCargo {
    USER("user"),
    ADMIN("admin");

    private String Cargo;




}
