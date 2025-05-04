package com.example.NORD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(precision = 2)
    private Integer id_Catalogo;
    @Column(length = 25)
    @NotNull
    private String nome_Catalogo;

    @OneToMany(mappedBy = "catalogo")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "catalogo_Sala")
    private List<Sala> salas;

    @OneToMany(mappedBy = "catalogo_Ingresso")
    private List<Ingresso> ingressos;
}
