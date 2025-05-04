//package com.example.NORD.model.Teste.Controller;
//
//
//
//import com.example.NORD.service.UsuarioService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("jj")
//@RequiredArgsConstructor
//public class TesteController {
//    private final UsuarioService usuarioService;
//
//
//    @PostMapping(path = "/Save")
//    public ResponseEntity<Teste> Save(@RequestBody TesteDTO testeDTO){
//       usuarioService.Save(testeDTO);
//       return new ResponseEntity<>(HttpStatus.CREATED);
//
//    }
//
//}
