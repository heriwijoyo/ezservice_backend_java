/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.integration;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizObjectMapperService.java, v 0.1 2024‐08‐19 8:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizObjectMapperService {

    Map<String, String> jsonToMap(String json);

    Map<String, String> parseSurveyResponse(String json);

    <T> T parseJson(String json, Class<T> clazz);

    <O, I> void parseFromSource(O output, I source);

    String toJson(Object object);
}