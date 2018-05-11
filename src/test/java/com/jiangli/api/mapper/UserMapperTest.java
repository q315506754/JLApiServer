package com.jiangli.api.mapper;

import com.jiangli.api.BaseTest;
import com.jiangli.api.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class UserMapperTest extends BaseTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void get() {
        //User course = userMapper.queryByUserId(100000039L) ;
        User course = userMapper.queryByUserId(1L) ;
        System.out.println(course);
    }
}