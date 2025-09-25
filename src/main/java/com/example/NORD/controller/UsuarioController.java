package com.example.NORD.controller;


import com.example.NORD.DTO.UsuarioDto;
import com.example.NORD.config.ConfiguraTokenServico;
import com.example.NORD.util.MapStruct;
import com.example.NORD.service.MudarSenhaService;
import com.example.NORD.model.Usuario;
import com.example.NORD.service.EmailService;
import com.example.NORD.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Nord")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ConfiguraTokenServico configuraTokenServico;
    private final EmailService emailService;


    @PostMapping(path = "/Registrar")
    public ResponseEntity<Usuario> usuarioResponseEntity(@RequestBody UsuarioDto usuarioDto){
        usuarioService.save(usuarioDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/Login")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioDto usuarioDto){
        Boolean validacao = usuarioService.login(usuarioDto);
        if (validacao) {
            Usuario usuario = MapStruct.INSTANCE.converterUsuario(usuarioDto);
            String token = configuraTokenServico.gerarToken(usuario);
            emailService.enviarEmail(usuarioDto.getEmail(), "Seja bem-vindo ao NORD", String.format("Aqui esta seu token para validação da conta: %s ",token));
            return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    @PutMapping(path = "/Mudarsenha")
    public ResponseEntity<Usuario> Sobrescrever(@RequestBody MudarSenhaService usuario){
        Boolean validacao = usuarioService.mudarSenha(usuario);
        if (validacao){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping(path = "/Deletarconta")
    public ResponseEntity<Boolean> Deletar(@RequestBody UsuarioDto usuarioDto){
        Boolean validar = usuarioService.deletar(usuarioDto);
        if (validar){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }




}
