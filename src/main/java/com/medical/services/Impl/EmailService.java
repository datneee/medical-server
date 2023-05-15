package com.medical.services.Impl;

import com.medical.entity.User;
import com.medical.models.Mail;
import com.medical.services.IEmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    final Configuration configuration;

    public EmailService(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void send(String subject, String content, String to, Boolean isHtmlFormat) throws MessagingException {
        if (isHtmlFormat == null) {
            isHtmlFormat = false;
        } else {
            isHtmlFormat = true;
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, isHtmlFormat, "UTF-8");
        helper.setSubject(subject);
        helper.setText(content, isHtmlFormat);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }
    public void sendEmail(Mail mail, String template) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getProps());

        final String tmp =  template;
        String html = templateEngine.process(tmp, context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        mailSender.send(message);
    }
    public void sendEmail(Mail mail, User user, String token) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getMailTo());
        String emailContent = getEmailContent(user, token);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }
    String getEmailContent(User user, String token) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("name", user.getFullName());
        model.put("token", token);
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
