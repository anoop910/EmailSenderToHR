package com.Anoop.Service;

import com.Anoop.Request.SendEmailReq;

public interface EmailService {
    public String emailSender(SendEmailReq sendEmailReq);
    public void reciveEmail();
}
