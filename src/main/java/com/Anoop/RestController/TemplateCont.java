package com.Anoop.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anoop.Request.SaveTemplate;
import com.Anoop.Response.GetAllTemplate;
import com.Anoop.Response.GetTemplateCode;
import com.Anoop.Service.TemplateService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/template")
@CrossOrigin("http://localhost:5173")
public class TemplateCont {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/all")
    public List<GetAllTemplate> getAllTemplatesRest(){
        return templateService.getAllTemplates();
    }
    
    @PostMapping("/save")
    public boolean saveTemplateRest(@RequestBody SaveTemplate saveTemplate){
        System.out.println("request come");
        return templateService.saveTemplate(saveTemplate);
    }

    @GetMapping("/code{id}")
    public GetTemplateCode getTemplateCodeById(@PathVariable Long id){
        return templateService.getTemplateCode(id);
    }
}
