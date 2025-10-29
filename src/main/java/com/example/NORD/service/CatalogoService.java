package com.example.NORD.service;


import com.example.NORD.model.Ingresso;
import com.example.NORD.repository.SalaRepository;
import com.example.NORD.repository.CatalogoRepository;
import com.example.NORD.service.impl.CatalogoServiceInterface;
import com.example.NORD.util.MapStruct;
import com.example.NORD.model.Catalogo;
import com.example.NORD.DTO.CatalogoDto;
import com.example.NORD.DTO.SalaDto;
import com.example.NORD.model.Sala;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogoService implements CatalogoServiceInterface {
    public final CatalogoRepository repositorio_Catalogo;
    public final SalaRepository salaRepository;


    Logger logger = LoggerFactory.getLogger(CatalogoService.class);

    public Catalogo criarCatalogo(CatalogoDto catalogo_Dto){
        Catalogo catalogodb = repositorio_Catalogo.findBynomeCatalogo(catalogo_Dto.getNomeCatalogo());

        if (catalogo_Dto.getNomeCatalogo() == null || catalogo_Dto.getNomeCatalogo().isEmpty() || catalogodb != null){

            return null;
        }


        return repositorio_Catalogo.save(MapStruct.INSTANCE.converterCatalogo(catalogo_Dto));

    }

    public Boolean adicionarSala(Integer idCatalogo, List<SalaDto> sala_Dto){

        Optional<Catalogo> catalogo = repositorio_Catalogo.findById(idCatalogo);
        List<Sala> sala = MapStruct.INSTANCE.converterSala(sala_Dto);


        if (catalogo.isEmpty() || sala_Dto == null){
            return false;
        }

        catalogo.get().getSalas().addAll(sala);
        repositorio_Catalogo.save(catalogo.get());
        for (Sala salaf : sala) {
            salaf.setCatalogo_Sala(catalogo.get());
            salaRepository.save(salaf);
        }
        return true;
    }

//    public List<Ingresso> criarIngressos(SalaDto sala_Dto){
//
//    }





}
