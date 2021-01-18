package com.example.registrationApp.domain.mail;

import com.example.registrationApp.domain.exception.server.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender{
    @Autowired
    private JavaMailSender mailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, "utf-8");
            messageHelper.setText(email, true);
            messageHelper.setTo(to);
            messageHelper.setSubject("CONFIRM YOUR ACCOUNT");
            messageHelper.setFrom("hello@luiz.com");
            mailSender.send(message);
        }catch (MessagingException messagingException){
            LOGGER.warn("Fail to send email:" + messagingException.getMessage());
            LOGGER.warn(messagingException.getLocalizedMessage());
            throw new ServerException("Could not send email.", 502);
        }
    }
}
