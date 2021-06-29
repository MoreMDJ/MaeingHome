package com.maeinghome.mybatisstudy;

import com.maeinghome.tool.launch.HomeApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.maeinghome.mybatisstudy.entity")
@SpringBootApplication
public class MybatisstudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisstudyApplication.class,args);
    }
}
