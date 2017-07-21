package com.example.demo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DemoApplicationTests;
import com.example.demo.core.service.HomeService;

public class HomeServiceTest extends DemoApplicationTests{
    @Autowired
    private HomeService homeService;
    
//    @Test
//    public void test1(){
//        System.out.println(homeService.testMybatis());
//    }
    
    @Test
    public void test2() throws Exception{
        System.out.println(homeService.get(""));
    }
}
