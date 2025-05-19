package com.example.NORD.service;


import com.example.NORD.Repositorio.Repositorio;
import com.example.NORD.excecoes.ExcecoesPersonalizadas;
import com.example.NORD.infra.TokenServico;
import com.example.NORD.mapstruct.MapStruct;
import com.example.NORD.model.DTO.Usuario_Dto;
import com.example.NORD.model.Usuario;
import com.example.NORD.model.UsuarioCargo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class Usuario_Service {

    public final Repositorio repositorio;


    Logger logger = LoggerFactory.getLogger(Usuario_Service.class);
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);


    public Usuario Save(Usuario_Dto usuarioDto){
        try {
            if (usuarioDto.getSenhaUsuario().length()>7){
                String senha_Codificada=bCrypt.encode(usuarioDto.getSenhaUsuario());
                usuarioDto.setSenhaUsuario(senha_Codificada);
                usuarioDto.setUsuarioCargos(UsuarioCargo.USER);
                return repositorio.save(MapStruct.INSTANCE.converter_usuario(usuarioDto));
            }

            else {
                throw new ExcecoesPersonalizadas("Senha muito pequena.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean Login(Usuario_Dto usuarioDto){
        try{
            Usuario usuarioDTO = MapStruct.INSTANCE.converter_usuario(usuarioDto);
            UserDetails banco = repositorio.findByemail(usuarioDTO.getEmail());


            if (banco == null|| banco.getPassword().isEmpty() || banco.getUsername().isEmpty()){
                throw new ExcecoesPersonalizadas("Verifique a sua senha e nome de usuário e tente novamente.");
            }

            boolean validacao = bCrypt.matches(usuarioDTO.getSenhaUsuario(),banco.getPassword());

            if (validacao){

                logger.info("PERMITIDO");
                return true;
            }
            else {
                logger.info("NEGADO");
                return false;

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean MudarSenha(Usuario_Dto usuarioDto, String senhaNova){
        try {
            boolean permissao = this.Login(usuarioDto);
            if (!permissao) {
                logger.info("Dados Invalidos");
                return false;
            }
            if (senhaNova.length()>7) {
                Usuario usuarioBanco = MapStruct.INSTANCE.converter_usuario(usuarioDto);
                usuarioBanco.setSenhaUsuario(senhaNova);
                repositorio.save(usuarioBanco);
                return true;
            }
            else {
                throw new ExcecoesPersonalizadas("Senha muito pequena.");
            }


        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }


}
