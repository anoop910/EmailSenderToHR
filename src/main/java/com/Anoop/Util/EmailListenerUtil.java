package com.Anoop.Util;

import java.util.Properties;
import org.eclipse.angus.mail.imap.IMAPFolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.Anoop.Telegram.TelegramNotifier;
import jakarta.mail.Address;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;

@Component
public class EmailListenerUtil {

    @Async
    public void startListener() {

        while (true) {
            try {
                System.out.println("⏳ Connecting to email server...");
                runEmailListener(); // Start listener
            } catch (Exception e) {
                System.err.println("❌ Listener crashed: " + e.getMessage());
                e.printStackTrace();

                System.out.println("🔄 Restarting listener in 5 seconds...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void runEmailListener() throws Exception {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props);
        Store store = session.getStore("imaps");

        store.connect("imap.gmail.com", "anoop91098@gmail.com", "");

        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        inbox.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent event) {
                try {
                    for (Message msg : event.getMessages()) {
                        String subject = msg.getSubject();
                        Address from = msg.getFrom()[0];

                        String text = "📩 New Email\n" +
                                "From: " + from + "\n" +
                                "Subject: " + subject;

                        TelegramNotifier.sendTelegramMessage(text);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        while (true) {
            try {
                 System.out.println("Listening for new emails... ");
                
                inbox.idle(); // BLOCKS until new email
            } catch (Exception idleError) {
                System.err.println("⚠ IMAP idle stopped. Need reconnect.");
                throw idleError; // throws to while(true) auto restart
            }
        }

    }

}
