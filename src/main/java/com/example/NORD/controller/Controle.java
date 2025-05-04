package com.example.NORD.controller;


import com.example.NORD.model.DTO.Usuario_Dto;
import com.example.NORD.model.Usuario;
import com.example.NORD.service.Usuario_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Nord")
@RequiredArgsConstructor
public class Controle {
    private final Usuario_Service usuarioService;

    @PostMapping(path = "/PostUsuario")
    public ResponseEntity<Usuario> usuarioResponseEntity(@RequestBody Usuario_Dto usuarioDto){
        usuarioService.Save(usuarioDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/Login")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario_Dto usuarioDto){
        Boolean validacao = usuarioService.Login(usuarioDto);
        if (validacao) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


}
