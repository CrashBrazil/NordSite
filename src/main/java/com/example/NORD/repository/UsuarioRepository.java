package com.example.NORD.repository;

import com.example.NORD.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
//    List<Usuario> findByemail(String email);
    UserDetails findByemail(String email);


}
