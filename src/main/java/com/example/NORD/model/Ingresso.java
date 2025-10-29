package com.example.NORD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(precision = 2)
    private UUID id_Ingresso;
    @Column(length = 20)
    @NotNull
    private String nome_Ingresso;


    @ManyToOne
    @JoinColumn(name = "id_Usuario_Fk")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_Catalogo_Fk")
    private Catalogo catalogo_Ingresso;
}
