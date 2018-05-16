package com.jiangli.api.controller

import com.jiangli.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
@RestController
@RequestMapping("/user")
 class LoginController : BaseController() {
    @Autowired
    var userService:UserService? = null

    /**
     * http://localhost:8010/user/login
     * @return
     */
    @RequestMapping("/login")
    fun login():String {
        log.debug("login33443...")
        return "ClientMain Hell5353反反复复付付付付 " + userService!!.queryByUserId(1L)
    }
}