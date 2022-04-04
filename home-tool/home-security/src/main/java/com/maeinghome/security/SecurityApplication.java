package com.maeinghome.security;

import com.maeinghome.tool.launch.HomeApplication;
import com.maeinghome.tool.launch.annotation.HomeBootApplication;

//@ComponentScan(basePackages = "com.maeinghome.security")
@HomeBootApplication(scanBasePackages = "com.maeinghome.security")
public class SecurityApplication {
    public static void main(String[] args) {
        HomeApplication.run("home-security", SecurityApplication.class, args);
    }
}