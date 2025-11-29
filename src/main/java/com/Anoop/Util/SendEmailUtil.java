package com.Anoop.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.Anoop.Request.SendEmailReq;

import jakarta.mail.MessagingException;
@Component
public class SendEmailUtil {

    @Autowired
    private JavaMailSender mailSender;

   
    public Boolean sendEmail(SendEmailReq sendEmailReq) {
        var message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(sendEmailReq.getSubject());
            helper.setText(sendEmailReq.getBody(), true);
            helper.setTo(sendEmailReq.getToEmail());
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

}
