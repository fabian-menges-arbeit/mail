package com.example.springboottest;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail() {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sender@test.org");
        mailMessage.setTo("receiver@test.org");
        mailMessage.setSubject("Subject");
        mailMessage.setText("Text");
        mailSender.send(mailMessage);
    }
}
