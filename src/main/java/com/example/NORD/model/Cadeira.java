package com.example.NORD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cadeira {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(precision = 2)
    private Integer id_Cadeira;
    @Column(precision = 2)
    @NotNull
    private Integer quantidadeCadeira;

    @ManyToOne
    @JoinColumn(name="id_Usuario_Fk")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_Sala_Fk")
    private Sala sala_Cadeira;


}
