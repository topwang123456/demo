package com.example.demo.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.model.Person;
import com.example.demo.core.service.HomeService;

@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private HomeService homeService;
    
    @RequestMapping("/hello")
    public String hello() {
        homeService.set();
        return homeService.get("xxxxx");
    }

    @RequestMapping("/testMybatis")
    public String testMybatis(){
        return homeService.testMybatis();
    }
}
