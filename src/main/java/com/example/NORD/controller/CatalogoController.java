package com.example.NORD.controller;

import com.example.NORD.DTO.CatalogoDto;
import com.example.NORD.DTO.SalaDto;
import com.example.NORD.model.Catalogo;
import com.example.NORD.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Nord")
@RequiredArgsConstructor
public class CatalogoController {
    private final CatalogoService catalogoService;

    @PostMapping(path = "/CriarCatalogo")
    public ResponseEntity<Catalogo> CriarCatalogo(@RequestBody CatalogoDto catalogoDto){
        Catalogo validar = catalogoService.criarCatalogo(catalogoDto);
        if (validar != null){
            return new ResponseEntity<>(validar,HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/Salasdocatalogo/{idcatalogo}")
    public ResponseEntity<Boolean> SalasCatalogo(@PathVariable("idcatalogo") Integer idcatalogo , @RequestBody List<SalaDto> salas){
        Boolean validar = catalogoService.adicionarSala(idcatalogo,salas);
        if (validar){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
