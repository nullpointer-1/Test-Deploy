package com.vehicle.car.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(String name, String email, String message, boolean isCTSEmployee) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("pointersnull77@gmail.com"); // where you receive
        mailMessage.setSubject("Contact Request from AutoShop");

        String body = "Name: " + name +
                "\nEmail: " + email +
                "\nCTS Employee: " + (isCTSEmployee ? "Yes 🎉" : "No") +
                "\n\nMessage:\n" + message;

        if (isCTSEmployee) {
            body += "\n\n🔥 This user is from CTS, consider giving a juicy discount!";
        }

        mailMessage.setText(body);
        mailMessage.setFrom("sanjeevsaisasank9@gmail.com");

        mailSender.send(mailMessage);
    }
}
