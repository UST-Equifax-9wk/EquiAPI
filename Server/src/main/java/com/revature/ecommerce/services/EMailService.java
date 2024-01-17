//package com.revature.ecommerce.services;
//
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EMailService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Async
//    public void sendEmail(String toEmail, String subject, String message){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(message);
//        mailMessage.setFrom("ecommercerus05@gmail.com");
//        javaMailSender.send(mailMessage);
//    }
//}
