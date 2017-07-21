package com.example.demo.core.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "key")
public class Person extends CommonEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private long sex;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        try {
            this.sex = Long.valueOf(sex);
        } catch (Exception e) {
            this.sex = 0L;
        }

    }

    public String getName() {
        return name;
    }

    public long getSex() {
        return sex;
    }
}
