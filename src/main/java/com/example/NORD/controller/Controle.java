package com.example.NORD.controller;


import com.example.NORD.infra.TokenServico;
import com.example.NORD.mapstruct.MapStruct;
import com.example.NORD.model.DTO.Usuario_Dto;
import com.example.NORD.model.MudarSenha;
import com.example.NORD.model.Usuario;
import com.example.NORD.service.EmailServico;
import com.example.NORD.service.Usuario_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Nord")
@RequiredArgsConstructor
public class Controle {
    private final Usuario_Service usuarioService;
    private final TokenServico tokenServico;
    private final EmailServico emailServico;


    @PostMapping(path = "/Registrar")
    public ResponseEntity<Usuario> usuarioResponseEntity(@RequestBody Usuario_Dto usuarioDto){
        usuarioService.Save(usuarioDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/Login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario_Dto usuarioDto){
        Boolean validacao = usuarioService.Login(usuarioDto);
        if (validacao) {
            Usuario usuario = MapStruct.INSTANCE.converter_usuario(usuarioDto);
            String token = tokenServico.gerarToken(usuario);
            emailServico.enviarEmail(usuarioDto.getEmail(), "Seja bem-vindo ao NORD", String.format("Aqui esta seu token para validação da conta: %s ",token));
            return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    @PutMapping(path = "/Mudarsenha")
    public ResponseEntity<Usuario> Sobrescrever(@RequestBody MudarSenha usuario){
        Boolean validacao = usuarioService.MudarSenha(usuario);
        if (validacao){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


}
