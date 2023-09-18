package uz.pdp.hrmanagement.service;

import org.thymeleaf.context.Context;

public interface MessageService {
    void sendHtmlMessage(String email, String subject, String templateName, Context context);
    void sendSimpleMessage(String email, String subject, String text);
}
