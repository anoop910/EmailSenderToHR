package com.Anoop.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Anoop.Request.SendEmailReq;
import com.Anoop.Telegram.TelegramNotifier;
import com.Anoop.Util.SendEmailUtil;


@Service
public class EmailServiceImpl implements EmailService {

   

    @Autowired
    private SendEmailUtil sendEmailUtil;

  

    @Override
    public String emailSender(SendEmailReq sendEmailReq, MultipartFile file) {

        Boolean isSent = sendEmailUtil.sendEmail(sendEmailReq, file);
        if (isSent) {
            String text = "Email  Sent to:  \n" + sendEmailReq.getToEmail(); 
            TelegramNotifier.sendTelegramMessage(text);

            System.out.println("email sent");
        }else{
            System.out.println("email not sent");
        }
        return "Email Sended";

    }

    @Override
    public void reciveEmail() {
        // try {
        //     Properties props = new Properties();
        //     props.put("mail.store.protocol", "imaps");

        //     Session session = Session.getDefaultInstance(props);
        //     Store store = session.getStore("imaps");

        //     store.connect("imap.gmail.com", "anoop91098@gmail.com","ixxi leho cwwq epia");

        //    IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        //     inbox.open(Folder.READ_ONLY);
            
        //     inbox.addMessageCountListener(new MessageCountAdapter() {
        //         @Override
        //         public void messagesAdded(MessageCountEvent event) {
        //             try {
        //                 for (Message message : event.getMessages()) {

        //                     String subject = message.getSubject();
        //                     Address from = message.getFrom()[0];
                            

        //                     String telegramText =
        //                             "📩 New Email Received\n"
        //                                     + "From: " + from + "\n"
        //                                     + "Subject: " + subject;

        //                     TelegramNotifier.sendTelegramMessage(telegramText);
        //                 }
        //             } catch (Exception e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     });

        //     // Enable real-time listening
        //     Thread idleThread = new Thread(() -> {
        //         try {
        //             int a = 0;
        //             while (true) {
        //                 System.out.println("Listening for new emails... "+a);
        //                 inbox.idle();
        //                 a++;
        //             }
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     });

        //     idleThread.start();
           
            
            

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

}
