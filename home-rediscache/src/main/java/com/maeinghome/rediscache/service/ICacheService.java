package com.maeinghome.rediscache.service;

public interface ICacheService {
    public String selectOne(String name ,String tel);

    public String selectDayOne(String name, String tel);

    public String updateOne(String name, String tel);

    public String updateAll();
}
