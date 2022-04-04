package com.maeinghome.admin;

import com.maeinghome.tool.launch.HomeApplication;
import com.maeinghome.tool.launch.annotation.HomeBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@HomeBootApplication
//@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        HomeApplication.run("home-admin", AdminApplication.class, args);
        //SpringApplication.run(AdminApplication.class, args);
    }

}
