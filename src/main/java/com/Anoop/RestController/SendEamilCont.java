package com.Anoop.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Anoop.ConstantConfi.AppProperties;
import com.Anoop.Request.SendEmailReq;
import com.Anoop.Service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/email")
@CrossOrigin("http://localhost:5173")
public class SendEamilCont {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppProperties appProperties;

    @PostMapping("/send")
    public String emailSender(
            @RequestParam("sendEmailReq") String sendEmailReqJson,
            @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        SendEmailReq sendEmailReq = objectMapper.readValue(sendEmailReqJson, SendEmailReq.class);

        // file may be null if not sent
        return emailService.emailSender(sendEmailReq, file);
    }

    @GetMapping("/recive")
    public String reciveEmail() {
        emailService.reciveEmail();
        return "get all reciveEmail";
    }

    @GetMapping
    public String check() {
        return appProperties.getMessage().get("welcome");

    }

}
