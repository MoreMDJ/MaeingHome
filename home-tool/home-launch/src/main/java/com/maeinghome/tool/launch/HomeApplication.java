package com.maeinghome.tool.launch;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.Assert;

import java.util.Properties;

public class HomeApplication {

    public static ConfigurableApplicationContext run(String appName, Class<?> primarySource, String... args) {
        SpringApplicationBuilder springApplicationBuilder = initSpringApplicationBuilder(appName, primarySource, args);
        return springApplicationBuilder.run(args);
    }

    public static SpringApplicationBuilder initSpringApplicationBuilder(String appName, Class<?> _class, String... args) {
        Assert.hasText(appName, "应用名:appName 不能为空");

        StandardEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new SimpleCommandLinePropertySource(args));
        propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, environment.getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment.getSystemEnvironment()));

        String[] activeProfiles = environment.getActiveProfiles();
        String profile = "dev";
        if (activeProfiles.length > 0) {
            profile = activeProfiles[0];

        }

        SpringApplicationBuilder builder = new SpringApplicationBuilder(_class);
        /*DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader(HomeApplication.class.getClassLoader());
        builder.application().setBanner(new ResourceBanner(defaultResourceLoader.getResource("banner.txt")));*/

        Properties properties = System.getProperties();
        properties.setProperty("spring.application.name", appName);
        properties.setProperty("spring.profiles.active", profile);

        return builder;
    }
}
