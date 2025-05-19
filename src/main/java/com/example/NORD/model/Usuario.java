package com.example.NORD.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_Usuario;

    @Column(length = 25)
    @NotNull
    private String nome_Usuario;
    @Column(length = 25)
    @NotNull
    private String sobrenome_Usuario;
    @Column(length = 11, unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.usuarioCargos == UsuarioCargo.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senhaUsuario;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
