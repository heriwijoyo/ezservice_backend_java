/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebOptionApiController.java, v 0.1 2024‐08‐17 3:40 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebOptionApiController {

    @PostMapping(value = "/webapp/option/organizations.json")
    private List<Map<String, String>> getOrganizations() {
        Map<String, String> orgMap = new HashMap<>();
        orgMap.put("value", "RJL0");
        orgMap.put("label", "RJL0");

        List<Map<String, String>> result = new ArrayList<>();
        result.add(orgMap);
        return result;
    }
}