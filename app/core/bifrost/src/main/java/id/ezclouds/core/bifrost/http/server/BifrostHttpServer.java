/**
 * Ezclouds.id
 * Copyright (c) 2020‐2022 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.http.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BifrostHttpServer.java, v 0.1 2022‐11‐04 11:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping("/api")
public class BifrostHttpServer {

    @GetMapping("/hallo")
    public String halo() {
        return "ALoha";
    }

}