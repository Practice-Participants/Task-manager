package ru.digitos.notification.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import ru.digitos.notification.service.EmailService;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    public void sendSimpleEmail(String email) {
        try {
            email = "admin@digit-os.ru";
            emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
        } catch (MailException mailException) {
            log.error("Error while sending out email..{}", mailException.getStackTrace());
       }
    }
}
