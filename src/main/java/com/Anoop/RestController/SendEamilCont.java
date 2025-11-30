package com.Anoop.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anoop.ConstantConfi.AppProperties;
import com.Anoop.Request.SendEmailReq;
import com.Anoop.Service.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin("http://localhost:5173")
public class SendEamilCont {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppProperties appProperties;
    
    @PostMapping("/send")
    public String emailSender(@RequestBody SendEmailReq sendEmailReq){
        System.out.println("request come");
        return emailService.emailSender(sendEmailReq);
    }

    @GetMapping("/recive")
    public String reciveEmail(){
        emailService.reciveEmail();
        return "get all reciveEmail";
    }

    @GetMapping
    public String check(){
        return appProperties.getMessage().get("welcome");
      
    }
    
}
