/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreObjectMapperService.java, v 0.1 2024‐08‐19 8:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreObjectMapperService implements BizObjectMapperService {

    @Override
    public Map<String, String> jsonToMap(String json) {
        try {
            return new ObjectMapper()
                    .readValue(json, new TypeReference<Map<String, String>>(){});
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, String> parseSurveyResponse(String json) {
        Map<String, String> objectMap = new HashMap<>();
        try {
            List<BizSurveyResponseItem> responseItems = new ObjectMapper()
                    .readValue(json, new TypeReference<List<BizSurveyResponseItem>>(){});

            for (BizSurveyResponseItem responseItem : responseItems) {
                if (responseItem.getSelectedOptions().size() > 0) {
                    List<String> selected = new ArrayList<>();
                    responseItem.getSelectedOptions().forEach(option -> {
                        selected.add(option.getValue());
                    });

                    objectMap.put(responseItem.getQuestionId() +"_a", String.join(",", selected));
                }
                objectMap.put(responseItem.getQuestionId() +"_o", responseItem.getOther());
            }
            return objectMap;

        } catch (JsonProcessingException e) {
            return objectMap;
        }
    }

    @Override
    public <T> T parseJson(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}