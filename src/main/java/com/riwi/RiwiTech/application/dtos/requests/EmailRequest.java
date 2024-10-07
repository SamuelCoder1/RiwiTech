package com.riwi.RiwiTech.application.dtos.requests;


import lombok.Data;

@Data
public class EmailRequest {
    private String to;        // Dirección de correo electrónico del destinatario
    private String subject;   // Asunto del correo
    private String body;      // Cuerpo del correo
}

