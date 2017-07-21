package com.example.demo.core.mapper.oracle;

import com.example.demo.core.model.Person;
import com.example.demo.spring.paginate.PageBounds;
import com.example.demo.spring.paginate.model.PageList;

public interface OracleHomeMapper {

    String selectSysdate();

    void insertTest();

    PageList<Person> selectUsers(PageBounds pageBoundsOrderID);
    
}
