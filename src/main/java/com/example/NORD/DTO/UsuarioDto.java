package com.example.NORD.DTO;


import com.example.NORD.enums.UsuarioCargoEnum;
import com.example.NORD.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(length = 25)
    @NotNull
    private String nomeUsuario;
    @Column(length = 25)
    @NotNull
    private String sobrenomeUsuario;
    @Column(length = 11, unique = true)
    @NotNull
    private String cpf;

    private LocalDate dataNascimento = null;
    @Column(length = 30, unique = true)
    @NotNull
    private String email;
    @NotNull
    private String senhaPessoa;
    private UsuarioCargoEnum cargoPessoa;

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
