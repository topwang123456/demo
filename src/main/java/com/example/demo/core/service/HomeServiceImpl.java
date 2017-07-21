package com.example.demo.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.mapper.mysql.HomeMapper;
import com.example.demo.core.mapper.oracle.OracleHomeMapper;
import com.example.demo.core.model.CommonEntity;
import com.example.demo.core.model.Person;
import com.example.demo.spring.paginate.model.PageList;
import com.example.demo.spring.utils.PageUtils;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired  
    RedisTemplate<Object, Object> redisTemplate;  
      
    @Resource(name="redisTemplate")  
    ValueOperations<Object,Object> valOps;  
    @Autowired
    private HomeMapper homeMapper;
    
    @Autowired
    private OracleHomeMapper oracleHomeMapper;

    @Override
    public void set() {
        valOps.set("11", 11);
    }

    @Override
    public String get(String name) {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) {
                byte[] b = connection.get("11".getBytes());
                return new String(b);
            }
        });
//        return valOps.get("11").toString();
    }

    @Transactional(readOnly = true)
    @Override
    public String testMybatis() {
        String sysdate = homeMapper.selectSysdate();
        PageList<Person> l = homeMapper.selectUsers(PageUtils.getPageBoundsOrderID(new Person()));
        return sysdate;
    }

    @Transactional
    @Override
    public List<Person> searchPersonList() {
//        oracleHomeMapper.selectUsers(PageUtils.getPageBoundsOrderID(new Person()));
        return homeMapper.selectUsers(PageUtils.getPageBoundsOrderID(new Person()));
    }

    @Transactional
    @Override
    public void insetTest() throws Exception{
       homeMapper.insertTest(); 
    }
    
    public static void main(String[] args) {
        System.out.println("xxxxx".getBytes());
    }
}
