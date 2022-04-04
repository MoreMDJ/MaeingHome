package com.maeinghome.mybatisstudy.controller;

import com.maeinghome.mybatisstudy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateSourceController {

    @Autowired
    TestService testService;

    @RequestMapping("/index")
    public String index(String name) {

        return testService.getName();
    }

}
