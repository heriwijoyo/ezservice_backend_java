/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BifrostWebApplication.java, v 0.1 2023‐06‐18 11:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@SpringBootApplication(scanBasePackages = {"id.ezclouds.core.bifrost", "id.ezclouds.biz.arahindonesia", "id.ezclouds.core.shared", "id.ezclouds.core.member", "id.ezclouds.core.auth"})
@ImportResource({ "classpath:/META-INF/ezclouds/spring/config.xml" })
@EnableJpaRepositories(basePackages = {"id.ezclouds.common.dal", "id.ezclouds.core.shared", "id.ezclouds.core.member", "id.ezclouds.core.auth"})
@EntityScan(basePackages = {"id.ezclouds.common.dal", "id.ezclouds.core.shared", "id.ezclouds.core.member", "id.ezclouds.core.auth"})
@EnableCaching
public class BifrostWebApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BifrostWebApplication.class, args);
    }
}

