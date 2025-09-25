package com.example.NORD.util;

import com.example.NORD.model.Catalogo;
import com.example.NORD.DTO.CatalogoDto;
import com.example.NORD.DTO.IngressoDto;
import com.example.NORD.DTO.SalaDto;
import com.example.NORD.DTO.UsuarioDto;
import com.example.NORD.model.Ingresso;
import com.example.NORD.model.Sala;
import com.example.NORD.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "Spring")
public abstract class MapStruct {
    public static final MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);

    public abstract Usuario converterUsuario(UsuarioDto usuarioDto);
    public abstract Catalogo converterCatalogo(CatalogoDto catalogo);
    public abstract List<Sala> converterSala(List<SalaDto> sala);
    public abstract Ingresso converterIngresso(IngressoDto ingresso);

}
