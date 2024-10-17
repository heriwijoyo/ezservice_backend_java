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
import id.ezclouds.common.model.util.ModelReflectionMapper;
import id.ezclouds.common.util.StringUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    public <O, I> void parseFromSource(O output, I source) {
        Map<String, String> parseMap = ModelReflectionMapper.getParserMap(source, output);
        parseFromSource(output, source, parseMap);
    }

    @Override
    public <O, I> void parseFromSource(O output, I source, Map<String, String> parseMap) {
        Map<String, Field> sourceFieldMap = new HashMap<>();
        for (Field mField : source.getClass().getDeclaredFields()) {
            sourceFieldMap.put(mField.getName(), mField);
        }

        for (Field outputField : output.getClass().getDeclaredFields()) {
            String fieldName = outputField.getName();
            String valueKey = parseMap.get(fieldName);

            if (StringUtil.isNotBlank(valueKey)) {
                if (valueKey.startsWith("INT_")) {
                    try {
                        outputField.setAccessible(true);
                        outputField.set(output, Integer.parseInt(valueKey.substring("INT_".length())));
                    } catch (Exception ignored) {
                    }
                }
                else if (valueKey.startsWith("STR_")) {
                    try {
                        outputField.setAccessible(true);
                        outputField.set(output, valueKey.substring("STR_".length()));
                    } catch (Exception ignored) {
                    }
                }
                else {
                    Field sourceField = sourceFieldMap.get(valueKey);
                    if (sourceField != null) {
                        try {
                            outputField.setAccessible(true);
                            sourceField.setAccessible(true);
                            outputField.set(output, sourceField.get(source));
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
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