package com.example.NORD.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServico {
    private JavaMailSender javaMailSender;


    public void enviarEmail(String destinatario, String assunto, String corpo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@gmail.com");
        simpleMailMessage.setTo(destinatario);
        simpleMailMessage.setSubject(assunto);
        simpleMailMessage.setText(corpo);
        javaMailSender.send(simpleMailMessage);
    }
}
