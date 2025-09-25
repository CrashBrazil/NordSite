package com.example.NORD.DTO;

import com.example.NORD.model.Cadeira;
import com.example.NORD.model.Catalogo;
import com.example.NORD.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(precision = 6)
    private Integer id_Sala;

    @NotNull
    @Column(precision = 2)
    private Integer quantidade_Cadeiras;

    @OneToMany(mappedBy = "sala_Usuario")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "sala_Cadeira")
    private List<Cadeira> cadeiras;

    @ManyToOne
    @JoinColumn(name="id_Catalogo_Fk")
    private Catalogo catalogo_Sala;
}
