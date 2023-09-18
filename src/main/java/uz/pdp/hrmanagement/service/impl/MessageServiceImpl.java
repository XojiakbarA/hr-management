package uz.pdp.hrmanagement.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uz.pdp.hrmanagement.event.UserCreateEvent;
import uz.pdp.hrmanagement.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @EventListener
    public void handleUserCreate(UserCreateEvent e) {
        Context context = new Context();
        context.setVariable("code", e.getCode());
        sendHtmlMessage(e.getEmail(), "Verify Email", "verify", context);
    }

    @Override
    public void sendHtmlMessage(String email, String subject, String templateName, Context context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(email);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void sendSimpleMessage(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
