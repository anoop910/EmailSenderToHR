package com.Anoop.Service;

import java.util.List;

import com.Anoop.Request.EditTemplate;
import com.Anoop.Request.SaveTemplate;
import com.Anoop.Response.GetAllTemplate;
import com.Anoop.Response.GetTemplateCode;


public interface TemplateService {
    public List<GetAllTemplate> getAllTemplates();
    public Boolean saveTemplate(SaveTemplate saveTemplate);
    public GetTemplateCode getTemplateCode(Long id);
    public Boolean editTemplate(Long id, EditTemplate editTemplate);
    public Boolean deleteTemplate(Long id);
}
