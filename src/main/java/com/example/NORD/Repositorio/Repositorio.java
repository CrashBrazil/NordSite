package com.example.NORD.Repositorio;

import com.example.NORD.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface Repositorio extends JpaRepository<Usuario,Integer> {
//    List<Usuario> findByemail(String email);
    UserDetails findByemail(String email);


}
