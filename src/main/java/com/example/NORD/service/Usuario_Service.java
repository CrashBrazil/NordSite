package com.example.NORD.service;


import com.example.NORD.Repositorio.Repositorio;
import com.example.NORD.excecoes.ExcecoesPersonalizadas;
import com.example.NORD.mapstruct.MapStruct;
import com.example.NORD.model.DTO.Usuario_Dto;
import com.example.NORD.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            List<Usuario> banco = repositorio.findByemail(usuarioDTO.getEmail());

            if (banco.isEmpty() || banco.get(0).getSenhaUsuario().isEmpty() || banco.get(0).getEmail().isEmpty()){
                throw new ExcecoesPersonalizadas("Verifique a sua senha e nome de usuário e tente novamente.");
            }

            boolean validacao = bCrypt.matches(usuarioDTO.getSenhaUsuario(),banco.get(0).getSenhaUsuario());

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


}
