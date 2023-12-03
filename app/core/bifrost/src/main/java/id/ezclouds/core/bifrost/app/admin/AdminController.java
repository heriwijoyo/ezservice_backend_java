/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminController.java, v 0.1 2023‐12‐03 11:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        return "Admin test";
    }
}