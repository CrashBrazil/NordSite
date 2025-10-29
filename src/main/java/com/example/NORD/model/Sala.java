package com.example.NORD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(precision = 6)
    private UUID id_Sala;
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
