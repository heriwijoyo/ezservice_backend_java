/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebFormController.java, v 0.1 2024‐11‐18 7:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebFormController {

    @GetMapping(value = "/forms/{formId}")
    private void renderForm(
            @PathVariable("formId") String formId,
            HttpServletResponse response) {


    }
}