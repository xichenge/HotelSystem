//package com.team.hotel.pojo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mail.MailProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//@EnableConfigurationProperties(MailProperties.class)
//public class MailConfig {
//
//    @Autowired
//    private MailProperties mailProperties;
//
//    @Bean
//    public JavaMailSenderImpl javaMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost(mailProperties.getHost());
//        javaMailSender.setPort(mailProperties.getPort());
//        javaMailSender.setUsername(mailProperties.getUsername());
//        javaMailSender.setPassword(mailProperties.getPassword());
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", mailProperties.getProperties().get("mail.smtp.auth"));
//        properties.put("mail.smtp.starttls.enable", mailProperties.getProperties().get("mail.smtp.starttls.enable"));
//
//        javaMailSender.setJavaMailProperties(properties);
//        return javaMailSender;
//    }
//}
