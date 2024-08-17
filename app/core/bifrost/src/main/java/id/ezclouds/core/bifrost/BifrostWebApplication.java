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
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BifrostWebApplication.java, v 0.1 2023‐06‐18 11:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@SpringBootApplication(scanBasePackages = {
        "id.ezclouds.biz.ezservice",
        "id.ezclouds.common.util",
        "id.ezclouds.core.admin",
        "id.ezclouds.core.auth",
        "id.ezclouds.core.bifrost",
        "id.ezclouds.core.dal",
        "id.ezclouds.core.integration",
        "id.ezclouds.core.member",
        "id.ezclouds.core.param",
        "id.ezclouds.core.process",
        "id.ezclouds.core.shared"
})
@ImportResource({ "classpath:/META-INF/ezclouds/spring/config.xml" })
@EnableJpaRepositories(basePackages = {
        "id.ezclouds.core.dal",
        "id.ezclouds.core.shared",
        "id.ezclouds.core.member",
        "id.ezclouds.core.auth",
        "id.ezclouds.core.integration.dataservice.repo",
        "id.ezclouds.biz.ezservice"
})
@EntityScan(basePackages = {
        "id.ezclouds.core.dal",
        "id.ezclouds.core.shared",
        "id.ezclouds.core.member",
        "id.ezclouds.core.auth",
        "id.ezclouds.core.integration.dataservice.dataobject",
        "id.ezclouds.biz.ezservice"
})
@EnableCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
public class BifrostWebApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BifrostWebApplication.class, args);
    }
}

