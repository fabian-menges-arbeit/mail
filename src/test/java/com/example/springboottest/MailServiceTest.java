package com.example.springboottest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;

@SpringBootTest
public class MailServiceTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);
    @Autowired
    MailService mailService;

    @Test
    void testMailService() throws IOException, MessagingException {
        mailService.sendMail();
        greenMail.waitForIncomingEmail(500, 1);
        var messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);
        var mimeMessage = messages[0];
        assertEquals("Text", mimeMessage.getContent().toString().trim());
        assertEquals("Subject", mimeMessage.getSubject());
    }
}
