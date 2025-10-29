package com.example.NORD.DTO;

import com.example.NORD.model.Ingresso;
import com.example.NORD.model.Sala;
import com.example.NORD.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoDto {
    @Column(length = 25)
    @NotNull
    private String nomeCatalogo;

    @OneToMany(mappedBy = "catalogo")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "catalogo_Sala",cascade = CascadeType.PERSIST)
    private List<Sala> salas;

    @OneToMany(mappedBy = "catalogo_Ingresso")
    private List<Ingresso> ingressos;

}
