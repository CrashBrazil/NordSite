package com.example.NORD.controller;

import com.example.NORD.DTO.IngressoDto;
import com.example.NORD.model.Ingresso;
import com.example.NORD.service.IngressoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/Nord")
@RequiredArgsConstructor
public class IngressoController {
    private final IngressoService ingressoService;

    @PostMapping(path = "/CriarIngresso")
    public ResponseEntity<Ingresso> ingressoResponseEntity(@RequestBody IngressoDto ingressoDto){
        Ingresso ingresso = ingressoService.criarIngresso(ingressoDto);
        if(ingresso != null){
            return new ResponseEntity<>(ingresso, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
