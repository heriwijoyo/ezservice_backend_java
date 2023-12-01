/**
 * Ezclouds.id
 * Copyright (c) 2020‐2022 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.http.server;

import id.ezclouds.common.facade.heimdall.HttpApiRouter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BifrostHttpServer.java, v 0.1 2022‐11‐04 11:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping("/v1")
public class BifrostHttpServer {

    @Resource(name = "httpApiRouter")
    HttpApiRouter httpApiRouter;

    @GetMapping("/hallo")
    public String halo() {
        return httpApiRouter.sayHello();
    }

}