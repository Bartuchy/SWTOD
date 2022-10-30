package com.example.swtod.configs.mailing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private static final String SUBJECT = "Dane logowania do systemu";
    private final JavaMailSender mailSender;
    @Value("${mail.host}")
    private String mailHost;

    public void sendEmail(String receiver, String password) {
        String message = generateMessage(receiver, password);
        SimpleMailMessage simpleMailMessage = mailConfig(receiver, message);

        this.mailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage mailConfig(String receiver, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mailHost);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(message);

        return simpleMailMessage;
    }

    private String generateMessage(String username, String password) {
        return "Login: " + username + "\nHasło: " + password;
    }
}
