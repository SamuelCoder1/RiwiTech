package com.riwi.RiwiTech.controllers;

import com.riwi.RiwiTech.application.dtos.requests.EmailRequest;
import com.riwi.RiwiTech.application.services.impl.EmailService;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);
        return ResponseEntity.ok("Email sent successfully");
    }
}
