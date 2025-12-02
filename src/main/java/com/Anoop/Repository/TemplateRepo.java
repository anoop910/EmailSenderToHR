package com.Anoop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Anoop.Model.Template;

@Repository
public interface TemplateRepo extends JpaRepository<Template, Long>{
    
}
