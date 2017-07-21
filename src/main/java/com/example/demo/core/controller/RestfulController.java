package com.example.demo.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.model.Person;
import com.example.demo.core.service.HomeService;

@RequestMapping("/restful")
@RestController
public class RestfulController {
    @Autowired
    private HomeService homeService;
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<Person> getPersonList(){
        return homeService.searchPersonList();
    }
}
