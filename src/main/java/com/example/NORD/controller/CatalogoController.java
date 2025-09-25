package com.example.NORD.controller;

import com.example.NORD.DTO.CatalogoDto;
import com.example.NORD.DTO.SalaDto;
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
    public ResponseEntity<Boolean> CriarCatalogo(@RequestBody CatalogoDto catalogoDto){
        Boolean validar = catalogoService.criarCatalogo(catalogoDto);
        if (validar){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping(path = "/Salasdocatalogo/{idcatalogo}")
    public ResponseEntity<Boolean> SalasCatalogo(@PathVariable("idcatalogo") Integer idcatalogo , @RequestBody List<SalaDto> salas){
        Boolean validar = catalogoService.adicionarSala(idcatalogo,salas);
        if (validar){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
