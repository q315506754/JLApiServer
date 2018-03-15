package com.jiangli.api.service;

import com.jiangli.api.model.User;

public interface UserService {
    User queryByUserId(Long userId);
}