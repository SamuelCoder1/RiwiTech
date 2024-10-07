package com.riwi.RiwiTech.application.services.impl;

import com.riwi.RiwiTech.application.dtos.requests.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import sendinblue.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.Collections;

@Service
public class EmailService {

    private final TransactionalEmailsApi transactionalEmailsApi;

    @Autowired
    public EmailService(TransactionalEmailsApi transactionalEmailsApi) {
        this.transactionalEmailsApi = transactionalEmailsApi;
    }

    public void sendEmail(EmailRequest emailRequest) {
        // Crea el objeto SendSmtpEmail
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                .sender(new SendSmtpEmailSender().email("tu-email@ejemplo.com")) // Cambia esto por tu dirección de envío
                .to(Collections.singletonList(new SendSmtpEmailTo().email(emailRequest.getTo())))
                .subject(emailRequest.getSubject())
                .htmlContent(emailRequest.getBody());

        try {
            // Envía el correo
            transactionalEmailsApi.sendTransacEmail(sendSmtpEmail);
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error al enviar el correo: " + e.getMessage());
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}
