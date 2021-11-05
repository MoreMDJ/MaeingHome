package com.maeinghome.thread;

import com.maeinghome.tool.launch.HomeApplication;
import com.maeinghome.tool.launch.annotation.HomeBootApplication;

@HomeBootApplication(scanBasePackages = "com.maeinghome.thread")
public class ThreadApplication {
    public static void main(String[] args) {
        HomeApplication.run("Thead", ThreadApplication.class, args);
    }
}
