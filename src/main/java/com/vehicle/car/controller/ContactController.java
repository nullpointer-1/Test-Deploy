package com.vehicle.car.controller;



import com.vehicle.car.DTO.ContactRequest;
import com.vehicle.car.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private EmailService emailService;
    // Endpoint to handle contact form submissionss

    @PostMapping("contact")
    public ResponseEntity<String> contactUs(@RequestBody ContactRequest request) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
        emailService.sendContactEmail(
                request.getName(),
                request.getEmail(),
                request.getMessage(),
                request.isCtsEmployee()
        );

        return ResponseEntity.ok("Message sent successfully!");
    }
}
