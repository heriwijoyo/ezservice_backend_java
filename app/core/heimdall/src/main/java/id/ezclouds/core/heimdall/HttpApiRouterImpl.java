/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.heimdall;

import id.ezclouds.common.facade.heimdall.HttpApiRouter;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: HttpAPIRouter.java, v 0.1 2023‐06‐19 12:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@Service("httpApiRouter")
public class HttpApiRouterImpl implements HttpApiRouter {

    @Override
    public String sayHello() {
        return "Alo Alo HA";
    }
}