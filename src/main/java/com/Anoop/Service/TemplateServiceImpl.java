package com.Anoop.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Anoop.Model.Template;
import com.Anoop.Repository.TemplateRepo;
import com.Anoop.Request.EditTemplate;
import com.Anoop.Request.SaveTemplate;
import com.Anoop.Response.GetAllTemplate;
import com.Anoop.Response.GetTemplateCode;

import jakarta.transaction.Transactional;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepo templateRepo;

    @Override
    public List<GetAllTemplate> getAllTemplates() {
        List<Template> templates = templateRepo.findAll();
        List<GetAllTemplate> getAllTemplates = new ArrayList<>();

        for (Template template : templates) {
            if (!template.isDeleted()) {
                GetAllTemplate getAllTemplate = new GetAllTemplate();
                getAllTemplate.setId(template.getId());
                getAllTemplate.setTitle(template.getTitle());
                getAllTemplates.add(getAllTemplate);
            }

        }
        return getAllTemplates;

    }

    @Override
    @Transactional
    public Boolean saveTemplate(SaveTemplate saveTemplate) {
        Template template = new Template();
        template.setTemplateCode(saveTemplate.getTemplateCode());
        template.setTitle(saveTemplate.getTitle());
        template.setDeleted(false);
        templateRepo.save(template);
        return true;

    }

    @Override
    public GetTemplateCode getTemplateCode(Long id) {
        Template template = templateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));

        GetTemplateCode templateCode = new GetTemplateCode();
        templateCode.setTemplateCode(template.getTemplateCode());
        return templateCode;

    }

    @Override
    public Boolean editTemplate(Long id, EditTemplate editTemplate) {
        Template template = templateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not fund with id : " + id));
        template.setTemplateCode(editTemplate.getTemplateCode());
        templateRepo.save(template);
        return true;
    }

    @Override
    public Boolean deleteTemplate(Long id) {
        Template template = templateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template Not found by :" + id));
        template.setDeleted(true);
        templateRepo.save(template);
        return true;
    }

}
