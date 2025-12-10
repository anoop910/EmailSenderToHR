package com.Anoop.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SendEmailReq {
    @NotNull
    private String toEmail;
    @NotNull
    private String subject;
    @NotNull
    private String body;
    private String bcc;
    private String cc;
   
}
