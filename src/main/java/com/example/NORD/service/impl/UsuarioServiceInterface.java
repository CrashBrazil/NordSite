package com.example.NORD.service.impl;

import com.example.NORD.DTO.UsuarioDto;
import com.example.NORD.enums.UsuarioCargoEnum;
import com.example.NORD.exception.PersonalizadaException;
import com.example.NORD.model.Usuario;
import com.example.NORD.service.MudarSenhaService;

import java.sql.SQLIntegrityConstraintViolationException;


public interface UsuarioServiceInterface {
    Usuario save(UsuarioDto usuarioDto) throws SQLIntegrityConstraintViolationException;
    Boolean login(UsuarioDto usuarioDto);
    Boolean mudarSenha(MudarSenhaService usuarioAntigo);
    Boolean deletar(UsuarioDto usuarioDto);

}
