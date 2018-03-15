package com.jiangli.api.impl;

import com.jiangli.api.mapper.UserMapper;
import com.jiangli.api.model.User;
import com.jiangli.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiangli
 * @date 2018/3/15 17:42
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByUserId(Long userId) {
        return userMapper.queryByUserId(userId);
    }
}
