package com.example.NORD.mapstruct;

import com.example.NORD.model.Catalogo;
import com.example.NORD.model.DTO.CatalogoDto;
import com.example.NORD.model.DTO.SalaDto;
import com.example.NORD.model.DTO.Usuario_Dto;
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

    public abstract Usuario converter_usuario(Usuario_Dto usuarioDto);
    public abstract Catalogo converter_catalogo(CatalogoDto catalogo);
    public abstract List<Sala> converter_sala(List<SalaDto> sala);

}
