package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.DemoApplicationTests;

public class HomeControllerTest extends DemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    WebApplicationContext applicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
    
    @Test
    public void testGetPersonList() throws Exception{
        String url = "/hello";
        RequestBuilder request = get(url);
        System.out.println(mvc.perform(request).andReturn().getResponse().getContentAsString());
    }
}
