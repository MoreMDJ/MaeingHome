package com.maeinghome.rediscache.service.impl;

import com.maeinghome.rediscache.service.ICacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CacheServiceImpl implements ICacheService {

    private static final String CACHEVALUE = "HALF:HOUR";
    private static final String CACHEVALUE_DAY = "HALF:DAY";

    @Override
    @Cacheable(value = CACHEVALUE,key = "#name + '_' + #tel")
    public String selectOne(String name, String tel) {
        System.out.println("name = " + name + ", tel = " + tel);
        Random r = new Random();
        return name +"_"+ tel +":" + r.nextInt(1000);
    }

    @Cacheable(value = CACHEVALUE_DAY,key = "#name + '_' + #tel")
    public String selectDayOne(String name, String tel) {
        System.out.println("name = " + name + ", tel = " + tel);
        Random r = new Random();
        return name +"_"+ tel +":" + r.nextInt(1000);
    }

    @Override
    @CacheEvict(value = {CACHEVALUE,CACHEVALUE_DAY}, key = "#name + '_' + #tel")
    public String updateOne(String name, String tel) {
        System.out.println("name = " + name + ", tel = " + tel);
        Random r = new Random();
        return name +"_"+ tel +":" + r.nextInt(1000);
    }

    @Override
    @CacheEvict(value = CACHEVALUE,allEntries=true)
    public String updateAll() {
        System.out.println("update All");
        return "update All";
    }
}
