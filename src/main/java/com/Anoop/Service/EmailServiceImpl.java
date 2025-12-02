package com.Anoop.Service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Anoop.ConstantConfi.MailProperties;
import com.Anoop.Request.SendEmailReq;
import com.Anoop.Util.SendEmailUtil;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.URLName;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Autowired
    private MailProperties mailProperties;

    @Override
    public String emailSender(SendEmailReq sendEmailReq) {

        sendEmailUtil.sendEmail(sendEmailReq);
        return "Email Sended";

    }

    @Override
    public void reciveEmail() {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore("imaps");
            System.out.println("===============" + mailProperties.getImap().get("password") + "===================");

            store.connect(mailProperties.getImap().get("host"), mailProperties.getImap().get("username"),
                    mailProperties.getImap().get("password"));

            Folder inbox = store.getFolder("[Gmail]/Sent Mail");
            Folder sent = store.getDefaultFolder();
           Folder[] defaultFolder = sent.list("*");
           for (Folder folder : defaultFolder) {
            System.out.println("================="+folder.getName()+ "===================");
           }
         
            
            inbox.open(Folder.READ_ONLY);
            System.out.println(inbox.getMessageCount());
            int end = inbox.getMessageCount();
            int start = Math.max(0, end - 20);

            Message[] messages = inbox.getMessages();

            for (Message msg : messages) {
                System.out.println("------------------------");
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("Subject: " + msg.getSubject());
                System.out.println("Body: " + msg.getContent());
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
