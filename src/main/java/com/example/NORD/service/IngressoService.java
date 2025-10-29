package com.example.NORD.service;


import com.example.NORD.repository.IngressoRepository;
import com.example.NORD.service.impl.IngressoServiceInterface;
import com.example.NORD.util.MapStruct;
import com.example.NORD.DTO.IngressoDto;
import com.example.NORD.model.Ingresso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class IngressoService implements IngressoServiceInterface {

    private final IngressoRepository ingressoRepository;

    public void criarIngresso(IngressoDto ingressoDto){
        Ingresso ingresso = MapStruct.INSTANCE.converterIngresso(ingressoDto);

        ingressoRepository.save(ingresso);
    }
    public void associarIngresso(){

    }
}
