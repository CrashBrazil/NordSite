package com.example.NORD.service;


import com.example.NORD.Repositorio.Repositorio;
import com.example.NORD.excecoes.ExcecoesPersonalizadas;
import com.example.NORD.mapstruct.MapStruct;
import com.example.NORD.model.DTO.Usuario_Dto;
import com.example.NORD.model.MudarSenha;
import com.example.NORD.model.Usuario;
import com.example.NORD.model.UsuarioCargo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
                usuarioDto.setUsuarioCargos(UsuarioCargo.valueOf("USER"));
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
    public Boolean MudarSenha(MudarSenha usuarioPassado){
        try {
            UserDetails usuarioBanco = repositorio.findByemail(usuarioPassado.email());
            Usuario usuarioFinal = (Usuario) usuarioBanco;
            boolean validacao = bCrypt.matches(usuarioPassado.senhaAntiga(),usuarioBanco.getPassword());

            if (usuarioPassado.senhaAntiga().isEmpty() || usuarioPassado.email().isEmpty() || usuarioPassado.novaSenha().isEmpty()) {
                throw new RuntimeException("isEmpty");
            }

            if (validacao && usuarioPassado.novaSenha().length()>7  ) {
                String senhaCondificada = bCrypt.encode(usuarioPassado.novaSenha());
                usuarioFinal.setSenhaUsuario(senhaCondificada);
                repositorio.save(usuarioFinal);
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

    public Boolean Deletar(Usuario_Dto usuarioDto){
        Usuario usuarioBanco = MapStruct.INSTANCE.converter_usuario(usuarioDto);
        if (usuarioBanco == null ) {
            throw new RuntimeException("Usuario não encontrado");
        }
        UserDetails encontrarUsuario = repositorio.findByemail(usuarioBanco.getEmail());
        if (encontrarUsuario != null && bCrypt.matches(usuarioDto.getSenhaUsuario(),encontrarUsuario.getPassword())){
            logger.info("Sucesso");
            repositorio.delete((Usuario) encontrarUsuario);
            return true;
        }
        else {
            logger.info("Email não encontrado ou senha invalida");
            return false;
        }


    }


}
