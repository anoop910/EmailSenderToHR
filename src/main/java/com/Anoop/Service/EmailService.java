package com.Anoop.Service;


import org.springframework.web.multipart.MultipartFile;

import com.Anoop.Request.SendEmailReq;

public interface EmailService {
    public String emailSender(SendEmailReq sendEmailReq, MultipartFile file);
    public void reciveEmail();
}
