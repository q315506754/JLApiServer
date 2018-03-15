package com.jiangli.api.controller;

import com.jiangli.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiangli
 * @date 2018/3/15 16:09
 */
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * http://localhost:8010/user/login
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        log.debug("login...");
        return "ClientMain Hello "+userService.queryByUserId(1L);
    }
}
