package com.medical.services;
import com.medical.entity.User;
import com.medical.models.Mail;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IEmailService {
    void send(String subject, String content, String to, Boolean isHtmlFormat) throws MessagingException;

    void sendEmail(Mail mail, String template) throws MessagingException, IOException;
    void sendEmail(Mail mail, User user, String token) throws MessagingException, IOException, TemplateException;
}
