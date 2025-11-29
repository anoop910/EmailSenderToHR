package com.Anoop.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anoop.ConstantConfi.AppProperties;
import com.Anoop.Request.SendEmailReq;
import com.Anoop.Service.SendEmailService;

@RestController
@RequestMapping("/email")
public class SendEamilCont {
    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private AppProperties appProperties;
    
    @PostMapping("/send")
    public String emailSender(@RequestBody SendEmailReq sendEmailReq){
        return sendEmailService.emailSender(sendEmailReq);
    }

    @GetMapping
    public String check(){
        return appProperties.getMessage().get("welcome");
      
    }
    
}
