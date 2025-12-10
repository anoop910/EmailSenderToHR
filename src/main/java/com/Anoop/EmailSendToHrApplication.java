package com.Anoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EmailSendToHrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSendToHrApplication.class, args);
	}

}
