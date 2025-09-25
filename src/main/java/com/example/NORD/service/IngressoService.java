package com.example.NORD.service;


import com.example.NORD.Repositorio.RepositorioIngresso;
import com.example.NORD.mapstruct.MapStruct;
import com.example.NORD.model.DTO.IngressoDto;
import com.example.NORD.model.Ingresso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class IngressoService {

    private final RepositorioIngresso repositorioIngresso;

    public void criarIngresso(IngressoDto ingressoDto){
        Ingresso ingresso = MapStruct.INSTANCE.converter_ingresso(ingressoDto);

        repositorioIngresso.save(ingresso);
    }
    public void associarIngresso(){



    }
}
