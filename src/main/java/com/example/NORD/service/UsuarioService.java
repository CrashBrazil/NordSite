package com.example.NORD.service;


import com.example.NORD.enums.UsuarioCargoEnum;
import com.example.NORD.repository.UsuarioRepository;
import com.example.NORD.exception.PersonalizadaException;
import com.example.NORD.service.impl.UsuarioServiceInterface;
import com.example.NORD.util.MapStruct;
import com.example.NORD.DTO.UsuarioDto;
import com.example.NORD.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;



@Service
@RequiredArgsConstructor

public class UsuarioService implements UsuarioServiceInterface {

    public final UsuarioRepository usuarioRepository;
    public final BCryptPasswordEncoder bcryptPasswordEncoder;
    Logger logger = LoggerFactory.getLogger(UsuarioService.class);



    public Usuario save(UsuarioDto usuarioDto) throws SQLIntegrityConstraintViolationException, IllegalArgumentException {

        Usuario usuarioDTO = MapStruct.INSTANCE.converterUsuario(usuarioDto);
        UserDetails usuarioNoBanco = usuarioRepository.findByemail(usuarioDTO.getEmail());

        if(usuarioNoBanco != null){
            throw new SQLIntegrityConstraintViolationException();
        }
        if (usuarioDto != null && usuarioDto.getSenhaPessoa().length()>7){
            String senha_Codificada=bcryptPasswordEncoder.encode(usuarioDto.getSenhaPessoa());
            usuarioDTO.setSenhaPessoa(senha_Codificada);
            usuarioDTO.setCargoPessoa(UsuarioCargoEnum.valueOf("USER"));
            return usuarioRepository.save(usuarioDTO);
        }

        else {
            throw new IllegalArgumentException();
        }

    }
    public Boolean login(UsuarioDto usuarioDto){

       try {
           Usuario usuarioDTO = MapStruct.INSTANCE.converterUsuario(usuarioDto);
           UserDetails usuarioNoBanco = usuarioRepository.findByemail(usuarioDTO.getEmail());

           if (usuarioNoBanco == null || usuarioNoBanco.getPassword().isEmpty() || usuarioNoBanco.getUsername().isEmpty()) {
               throw new RuntimeException();
           }

           boolean validarSenha = bcryptPasswordEncoder.matches(usuarioDTO.getSenhaPessoa(), usuarioNoBanco.getPassword());

           if (validarSenha) {
               logger.info("PERMITIDO");
               return true;
           } else {
               logger.info("NEGADO");
               return false;

           }
       }
       catch (RuntimeException e){
           return false;
       }

    }
    public Boolean mudarSenha(MudarSenhaService usuarioAntigo) {

        UserDetails usuarioNoBanco = usuarioRepository.findByemail(usuarioAntigo.email());
        Usuario usuarioNovo = (Usuario) usuarioNoBanco;
        if (usuarioNoBanco == null){
            logger.error("NullPointerException");
            return false;
        }
        boolean validarSenha = bcryptPasswordEncoder.matches(usuarioAntigo.senhaAntiga(),usuarioNoBanco.getPassword());


        if (usuarioAntigo.senhaAntiga().isEmpty() || usuarioAntigo.email().isEmpty() || usuarioAntigo.novaSenha().isEmpty()) {
            logger.error("IllegalArgumentException");
            return false;

        }

        if (validarSenha && usuarioAntigo.novaSenha().length()>7  ) {
            String senhaCondificada = bcryptPasswordEncoder.encode(usuarioAntigo.novaSenha());
            usuarioNovo.setSenhaPessoa(senhaCondificada);
            usuarioRepository.save(usuarioNovo);
            return true;
        }
        else {
            return false;
        }





    }

    public Boolean deletar(UsuarioDto usuarioDto){

        Usuario usuarioArmazenadoNoBanco = MapStruct.INSTANCE.converterUsuario(usuarioDto);

        UserDetails pesquinarNoBancoUsuario = usuarioRepository.findByemail(usuarioArmazenadoNoBanco.getEmail());
        if (pesquinarNoBancoUsuario != null && bcryptPasswordEncoder.matches(usuarioDto.getSenhaPessoa(),pesquinarNoBancoUsuario.getPassword())){
            usuarioRepository.delete((Usuario) pesquinarNoBancoUsuario);
            return true;
        }
        else {
            logger.info("Email não encontrado ou senha invalida");
            return false;
        }
    }


}
