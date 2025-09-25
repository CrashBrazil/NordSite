package com.example.NORD.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum UsuarioCargoEnum {
    USER("user"),
    ADMIN("admin");

    private final String Cargo;




}
