package com.jiangli.api.mapper;

import com.jiangli.api.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018-01-17 14:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void get() {
        //User course = userMapper.queryByUserId(100000039L) ;
        User course = userMapper.queryByUserId(1L) ;
        System.out.println(course);
    }
}