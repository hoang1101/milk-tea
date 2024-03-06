package com.api.milktea.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Locale;
import java.util.Map;

@Service
public class EmailService {
//    @Autowired
//    private JavaMailSender mailSender;
//
//
//    private final TemplateEngine templateEngine = new TemplateEngine();
//
    @Value("${spring.mail.username}") private String sender;
//
//
//
//
//    public void sendEmail(Email email, Map<String, Object> model ) {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
//            helper.setFrom(sender);
//            helper.setTo(email.getToEmail());
//            helper.setSubject(email.getSubject());
//            String content = templateEngine.process("email-template",new Context(Locale.getDefault(),model));
//            helper.setText(content,true);
//
////            FileSystemResource file
////                    = new FileSystemResource(
////                    new File(email.getAttachment()));
////
////            helper.addAttachment(
////                    file.getFilename(), file);
//
//
//            mailSender.send(mimeMessage);
//
//        } catch (MessagingException e){
////            return false;
//            e.printStackTrace();
//        }
//
//    }
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);

            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());

            String content = templateEngine.process("email-template", new Context(Locale.getDefault()));
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
