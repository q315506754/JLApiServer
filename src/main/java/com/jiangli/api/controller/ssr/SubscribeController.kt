package com.jiangli.api.controller.ssr

import com.jiangli.api.controller.BaseController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
@RestController
@RequestMapping("/xxr")
 class SubscribeController : BaseController() {

    /**
     * http://localhost:8010/xxr/list
     * @return
     */
    @RequestMapping("/list")
    fun login():String {
        log.debug("list...")
        return "ClientMain Hell5353反反复复付付付付"
    }
}