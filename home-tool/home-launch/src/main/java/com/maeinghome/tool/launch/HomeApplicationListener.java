package com.maeinghome.tool.launch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
public class HomeApplicationListener {

    public HomeApplicationListener() {
    }

    @Async
    @EventListener({ApplicationReadyEvent.class})
    public void afterStart(ApplicationReadyEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty(property) ? "8080" : property;
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("\n---[{}]---01启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{appName, localPort, profile});
    }


}
