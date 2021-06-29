package com.maeinghome.tool.launch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
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
    @Order
    @EventListener({ApplicationStartedEvent.class})
    public void afterStart(ApplicationStartedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty(property) ? "8080" : property;;
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("\n---[{}]---00启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{appName, localPort, profile});
    }

    @Async
    @Order(Integer.MIN_VALUE)
    @EventListener({ApplicationReadyEvent.class})
    public void afterStart(ApplicationReadyEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty(property) ? "8080" : property;;
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("\n---[{}]---01启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{appName, localPort, profile});
    }

    @Async
    @Order(Integer.MAX_VALUE)
    @EventListener({WebServerInitializedEvent.class})
    public void afterStart(WebServerInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty(property) ? "8080" : property;;
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("\n---[{}]---02启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{appName, localPort, profile});
    }



    @Async
    @Order(Integer.MIN_VALUE)
    @EventListener({ApplicationContextInitializedEvent.class})
    public void afterStart(ApplicationContextInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name").toUpperCase();
        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty(property) ? "8080" : property;;
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("\n---[{}]---03启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{appName, localPort, profile});
    }

    @Async
    @Order(Integer.MIN_VALUE)
    @EventListener({ApplicationStartingEvent.class})
    public void afterStart(ApplicationStartingEvent event) {
//        Environment environment = event.getSpringApplication().setBanner();
//        String appName = environment.getProperty("spring.application.name").toUpperCase();
//        String property = environment.getProperty("server.port");
        String localPort = ObjectUtils.isEmpty("property") ? "8080" : "property";
//        String profile = StringUtils.arrayToCommaDelimitedString("environment.getActiveProfiles()");
        log.info("\n---[{}]---03启动完成，当前使用的端口:[{}]，配置文件:[{}]---", new Object[]{"appName", localPort, "profile"});
    }





}
