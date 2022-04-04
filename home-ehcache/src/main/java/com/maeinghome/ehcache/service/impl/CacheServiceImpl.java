package com.maeinghome.ehcache.service.impl;

import com.maeinghome.ehcache.service.ICacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CacheServiceImpl implements ICacheService {

    @Cacheable(value = "HALF_HOUR", key = "#name + '_' + #tel")
    public String selectOne(String name, String tel) {
        System.out.println("name = " + name + ", tel = " + tel);
        Random r = new Random();
        return name + "_" + tel + ":" + r.nextInt(10);
    }
}
