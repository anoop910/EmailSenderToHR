package com.Anoop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Anoop.Model.SendEmail;

@Repository
public interface SendEmailRepo extends JpaRepository<SendEmail, Long> {
    
}
