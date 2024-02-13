/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.converter.BizModelConverter;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppMessageTemplate;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppCommonMessageTemplateRepository;
import id.ezclouds.common.dal.repo.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfigService.java, v 0.1 2023‐12‐09 11:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Autowired
    private AppCommonMessageTemplateRepository appCommonMessageTemplateRepository;

    @Cacheable("appConfig")
    public List<AppConfig> getAppConfigs() {
        return appConfigRepository
                .findAll()
                .stream()
                .map(BizModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Cacheable("appMessageTemplate")
    public List<AppMessageTemplate> getMessageTemplates() {
        return appCommonMessageTemplateRepository
                .findAll()
                .stream()
                .map(templateDO -> new AppMessageTemplate(templateDO.getTemplateId(), templateDO.getTemplateValue()))
                .collect(Collectors.toList());
    }

    public String getMessageTemplate(String templateId) {
        return getMessageTemplates()
                .stream()
                .filter(template -> templateId.equals(template.getId()))
                .findFirst()
                .orElse(new AppMessageTemplate(null, null))
                .getValue();
    }
}