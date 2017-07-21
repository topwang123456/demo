package com.example.demo.core.service;

import java.util.List;

import com.example.demo.core.model.Person;

public interface HomeService {

    void set();

    String get(String name);

    String testMybatis();

    List<Person> searchPersonList();
    
    void insetTest() throws Exception;
}
