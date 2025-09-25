package com.example.NORD.repository;

import com.example.NORD.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {
    Catalogo findBynomeCatalogo(String nomeCatalogo);

}

