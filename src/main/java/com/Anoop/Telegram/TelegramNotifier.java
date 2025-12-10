package com.Anoop.Telegram;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class TelegramNotifier {

    private static final String BOT_TOKEN = "";
    private static final String CHAT_ID = "";

    public static void sendTelegramMessage(String text) {
        try {
            String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> body = new HashMap<>();
            body.put("chat_id", CHAT_ID);
            body.put("text", text);

            restTemplate.postForObject(url, body, String.class);

            System.out.println("Telegram message sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

