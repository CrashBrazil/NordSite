package com.example.NORD.service.impl;

import com.example.NORD.DTO.CatalogoDto;
import com.example.NORD.DTO.SalaDto;
import com.example.NORD.model.Catalogo;

import java.util.List;

public interface CatalogoServiceInterface {
    Catalogo criarCatalogo(CatalogoDto catalogo_Dto);
    Boolean adicionarSala(Integer idCatalogo, List<SalaDto> sala_Dto);
}
