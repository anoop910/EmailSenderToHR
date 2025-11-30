package com.Anoop.ConstantConfi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring")
public class MailProperties {

    Map<String, String> imap = new HashMap<>();

    
}