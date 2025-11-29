package com.Anoop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Anoop.Request.SendEmailReq;
import com.Anoop.Util.SendEmailUtil;

@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Override
    public String emailSender(SendEmailReq sendEmailReq) {

        sendEmailUtil.sendEmail(sendEmailReq);
        return "Email Sended";
       
    }
    
}
