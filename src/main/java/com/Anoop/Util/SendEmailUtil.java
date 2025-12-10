package com.Anoop.Util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.Anoop.Request.SendEmailReq;

import jakarta.mail.MessagingException;

@Component
public class SendEmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public Boolean sendEmail(SendEmailReq sendEmailReq, MultipartFile file) {
        var message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(sendEmailReq.getSubject());
            helper.setText(sendEmailReq.getBody(), true);
            helper.setTo(sendEmailReq.getToEmail());

            if (!sendEmailReq.getBcc().isEmpty() || !sendEmailReq.getBcc().isBlank()) {
                helper.setBcc(sendEmailReq.getBcc());
            }
            if (!sendEmailReq.getCc().isEmpty() || !sendEmailReq.getCc().isBlank()) {
                helper.setCc(sendEmailReq.getCc());
            }
            if (file != null && !file.isEmpty()) {

                // Optional: PDF validation
                String contentType = file.getContentType();
                if (contentType != null) {
                    if (!contentType.equalsIgnoreCase("application/pdf")) {
                        throw new IllegalArgumentException("Only PDF files allowed");
                    }
                }

                String filename = file.getOriginalFilename();
                if (filename != null) {
                    helper.addAttachment(
                            filename,
                            new ByteArrayResource(file.getBytes()));
                }
            }

            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
