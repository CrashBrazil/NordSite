package com.example.NORD.service;


import com.example.NORD.enums.UsuarioCargoEnum;
import com.example.NORD.repository.UsuarioRepository;
import com.example.NORD.exception.PersonalizadaException;
import com.example.NORD.util.MapStruct;
import com.example.NORD.DTO.UsuarioDto;
import com.example.NORD.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UsuarioService {

    public final UsuarioRepository usuarioRepository;




    Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);


    public Usuario save(UsuarioDto usuarioDto){
        try {
            if (usuarioDto.getSenhaPessoa().length()>7){
                String senha_Codificada=bCrypt.encode(usuarioDto.getSenhaPessoa());
                usuarioDto.setSenhaPessoa(senha_Codificada);
                usuarioDto.setCargoPessoa(UsuarioCargoEnum.valueOf("USER"));
                return usuarioRepository.save(MapStruct.INSTANCE.converterUsuario(usuarioDto));
            }

            else {
                throw new PersonalizadaException("Senha muito pequena.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean login(UsuarioDto usuarioDto){
        try{
            Usuario usuarioDTO = MapStruct.INSTANCE.converterUsuario(usuarioDto);
            UserDetails usuarioNoBanco = usuarioRepository.findByemail(usuarioDTO.getEmail());


            if (usuarioNoBanco == null|| usuarioNoBanco.getPassword().isEmpty() || usuarioNoBanco.getUsername().isEmpty()){
                throw new PersonalizadaException("Verifique a sua senha e nome de usuário e tente novamente.");
            }

            boolean validarSenha = bCrypt.matches(usuarioDTO.getSenhaPessoa(),usuarioNoBanco.getPassword());

            if (validarSenha){
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
    public Boolean mudarSenha(MudarSenhaService usuarioAntigo){
        try {
            UserDetails usuarioNoBanco = usuarioRepository.findByemail(usuarioAntigo.email());
            Usuario usuarioNovo = (Usuario) usuarioNoBanco;
            boolean validarSenha = bCrypt.matches(usuarioAntigo.senhaAntiga(),usuarioNoBanco.getPassword());

            if (usuarioAntigo.senhaAntiga().isEmpty() || usuarioAntigo.email().isEmpty() || usuarioAntigo.novaSenha().isEmpty()) {
                throw new RuntimeException("isEmpty");
            }

            if (validarSenha && usuarioAntigo.novaSenha().length()>7  ) {
                String senhaCondificada = bCrypt.encode(usuarioAntigo.novaSenha());
                usuarioNovo.setSenhaPessoa(senhaCondificada);
                usuarioRepository.save(usuarioNovo);
                return true;
            }
            else {
                throw new PersonalizadaException("Senha muito pequena.");
            }


        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Boolean deletar(UsuarioDto usuarioDto){
        Usuario usuarioArmazenadoNoBanco = MapStruct.INSTANCE.converterUsuario(usuarioDto);

        if (usuarioArmazenadoNoBanco == null ) {
            throw new RuntimeException("Usuario não encontrado");
        }
        UserDetails pesquinarNoBancoUsuario = usuarioRepository.findByemail(usuarioArmazenadoNoBanco.getEmail());
        if (pesquinarNoBancoUsuario != null && bCrypt.matches(usuarioDto.getSenhaPessoa(),pesquinarNoBancoUsuario.getPassword())){
            logger.info("Sucesso");
            usuarioRepository.delete((Usuario) pesquinarNoBancoUsuario);
            return true;
        }
        else {
            logger.info("Email não encontrado ou senha invalida");
            return false;
        }


    }


}
