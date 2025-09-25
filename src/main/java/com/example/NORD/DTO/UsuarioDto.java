package com.example.NORD.DTO;


import com.example.NORD.enums.UsuarioCargoEnum;
import com.example.NORD.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    @Column(length = 25)
    @NotNull
    private String nome_Usuario;
    @Column(length = 25)
    @NotNull
    private String sobrenome_Usuario;
    @Column(length = 11,unique = true)
    @NotNull
    private String cpf;
    @NotNull
    private Date diaAniversario;
    @Column(length = 30, unique = true)
    @NotNull
    private String email;
    @NotNull
    private String senhaUsuario;
    private UsuarioCargoEnum usuarioCargosEnum;

    @OneToMany(mappedBy = "usuario")
    private List<Ingresso> ingresso;

    @OneToMany(mappedBy = "usuario" )
    private List<Cadeira> cadeiras;

    @ManyToOne
    @JoinColumn(name = "id_Catalogo_Fk")
    private Catalogo catalogo;

    @ManyToOne
    @JoinColumn(name = "id_Sala_FK")
    private Sala sala_Usuario;


}
