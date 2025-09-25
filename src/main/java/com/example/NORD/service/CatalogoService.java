package com.example.NORD.service;


import com.example.NORD.repository.SalaRepository;
import com.example.NORD.repository.CatalogoRepository;
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
public class CatalogoService {
    public final CatalogoRepository repositorio_Catalogo;
    public final SalaRepository salaRepository;


    Logger logger = LoggerFactory.getLogger(CatalogoService.class);

    public Boolean criarCatalogo(CatalogoDto catalogo_Dto){
        try {
            Catalogo catalogodb = repositorio_Catalogo.findBynomeCatalogo(catalogo_Dto.getNomeCatalogo());
            if (catalogo_Dto.getNomeCatalogo() == null || catalogo_Dto.getNomeCatalogo().isEmpty() || catalogodb != null){
                logger.error("Erro de verificão");
                return false;
            }
            repositorio_Catalogo.save(MapStruct.INSTANCE.converterCatalogo(catalogo_Dto));
            logger.info("Sucesso");
            return true;

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public Boolean adicionarSala(Integer idCatalogo, List<SalaDto> sala_Dto){
        try {
            Optional<Catalogo> catalogo = repositorio_Catalogo.findById(idCatalogo);
            List<Sala> sala = MapStruct.INSTANCE.converterSala(sala_Dto);


            if (catalogo.isEmpty() || sala_Dto == null){
                logger.error("Falha ao adicionar sala");
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
        catch (Exception e){
            throw new RuntimeException(e);
        }


    }


}
