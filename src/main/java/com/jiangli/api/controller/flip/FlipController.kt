package com.jiangli.api.controller.flip

import com.jiangli.api.controller.BaseController
import com.jiangli.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
//@RestController
@RequestMapping("/flip")
@Controller
 class FlipController : BaseController() {
    @Autowired
    var userService:UserService? = null

    /**
     *
     * http://localhost:8010/flip/index
     * @return
     */
    @RequestMapping("/index")
    fun login():ModelAndView {
        log.debug("login4444...")
        return ModelAndView("flip/index")
    }
//    fun login():String {
//        log.debug("login33443...")
//        return "index"
//    }
}