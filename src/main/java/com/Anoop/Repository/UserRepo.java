package com.Anoop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Anoop.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
}
