/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BifrostWebApplication.java, v 0.1 2023‐06‐18 11:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@ImportResource({ "classpath:/META-INF/ezclouds/spring/config.xml" })
@SpringBootApplication(scanBasePackages = "id.ezclouds.core.bifrost,id.ezclouds.core.heimdall")
public class BifrostWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BifrostWebApplication.class, args);
    }
}