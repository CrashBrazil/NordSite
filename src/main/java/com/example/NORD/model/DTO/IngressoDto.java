package com.example.NORD.model.DTO;

import com.example.NORD.model.Catalogo;
import com.example.NORD.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngressoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(precision = 2)
    private Integer id_Ingresso;
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
