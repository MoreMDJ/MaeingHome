package com.maeinghome.ehcache;

import com.maeinghome.ehcache.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class EhcacheApplication {
    @Autowired
    private ICacheService cacheService;

    public static void main(String[] args) {
        //HomeApplication.run("home-ehcache",EhcacheApplication.class,args);
        SpringApplication.run(EhcacheApplication.class, args);
    }

    @RequestMapping("/index")
    public String userString(String name, String tel) {
        return cacheService.selectOne(name, tel);
    }
}
