package com.example.NORD.model;

import com.example.NORD.enums.UsuarioCargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID idUsuario;

    @Column(length = 25)
    @NotNull
    protected String nomeUsuario;
    @Column(length = 25)
    @NotNull
    protected String sobrenomeUsuario;
    @Column(length = 11, unique = true)
    @NotNull
    protected String cpf;
    protected LocalDate dataNascimento = null;
    @Column(length = 30, unique = true)
    @NotNull
    protected String email;
    @NotNull
    protected String senhaPessoa;
    protected UsuarioCargoEnum cargoPessoa;
}
