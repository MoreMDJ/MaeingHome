package com.maeinghome.rediscache;

import com.maeinghome.rediscache.service.ICacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCaching
@RestController
public class RediscacheApplication {
    @Resource
    private ICacheService cacheService;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RediscacheApplication.class, args);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            String beanDefinitionName = beanDefinitionNames[i];
            System.out.println("自动配置bean: " + (i + 1) + "、" + beanDefinitionName);
        }
    }

    @RequestMapping("/index")
    public String selectOne(String name, String tel) {
        return cacheService.selectOne(name, tel);
    }

    @RequestMapping("/index/day")
    public String selectDayOne(String name, String tel) {
        return cacheService.selectDayOne(name, tel);
    }

    @RequestMapping("/index/updateone")
    public String updateOne(String name, String tel) {
        return cacheService.updateOne(name, tel);
    }

    @RequestMapping("/index/updateall")
    public String updateAll(String name, String tel) {
        return cacheService.updateAll();
    }

    @RequestMapping("/index/user/{id}")
    public String update(String name, String tel, @PathVariable String id) {
        return id;
    }
}
