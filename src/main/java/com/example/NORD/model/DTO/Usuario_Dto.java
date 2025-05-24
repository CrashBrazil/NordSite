package com.example.NORD.model.DTO;

import com.example.NORD.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario_Dto {

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
    private UsuarioCargo usuarioCargos;

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
