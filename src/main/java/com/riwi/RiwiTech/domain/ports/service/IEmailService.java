package com.riwi.RiwiTech.domain.ports.service;

import com.riwi.RiwiTech.application.dtos.requests.EmailRequest;

public interface IEmailService {
    void sendEmail(EmailRequest emailRequest);
}