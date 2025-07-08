package com.example.NORD.Repositorio;

import com.example.NORD.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioCatalogo extends JpaRepository<Catalogo, Integer> {
    Catalogo findBynomeCatalogo(String nomeCatalogo);

}

