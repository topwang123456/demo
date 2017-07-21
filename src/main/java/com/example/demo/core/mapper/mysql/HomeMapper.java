package com.example.demo.core.mapper.mysql;

import com.example.demo.core.model.Person;
import com.example.demo.spring.paginate.PageBounds;
import com.example.demo.spring.paginate.model.PageList;

public interface HomeMapper {

    String selectSysdate();

    void insertTest();

    PageList<Person> selectUsers(PageBounds pageBoundsOrderID);
    
}
