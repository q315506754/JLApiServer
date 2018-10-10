package com.jiangli.api.controller.ssr

import com.jiangli.api.controller.BaseController
import com.jiangli.api.service.ShareSSRService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
@RestController
@RequestMapping("/xxr")
 class SubscribeController : BaseController() {
    @Autowired
    var shareSSR: ShareSSRService? = null

    /**
     * http://localhost:8010/xxr/list
     * @return
     */
    @RequestMapping("/list")
    fun login():String {
        log.debug("list...")

//        val str = """
//ssr://MjA5LjU4LjE4OC4xMjo4MDk3Om9yaWdpbjphZXMtMjU2LWNmYjpwbGFpbjpaVWxYTUVSdWF6WTVORFUwWlRadVUzZDFjM0IyT1VSdFV6SXdNWFJSTUVRLz9vYmZzcGFyYW09Jmdyb3VwPWFHOXVaMnM
//ssr://MTcyLjEwNS4yMzAuMTIwOjEwMjU6b3JpZ2luOnJjNC1tZDU6cGxhaW46UTJ4Qk9WZGEvP29iZnNwYXJhbT0mcmVtYXJrcz01cGVsNXB5c0lPV0ZqZWkwdWVpS2d1ZUN1VEhtczZqbGhvemxoWTNvdExua3ZiX25sS2hxYzNOemRDNTBiM0EmZ3JvdXA9NXBlbDVweXM
//ssr://NDcuODguMTU5LjEwNDoxMDI1Om9yaWdpbjpyYzQtbWQ1OnBsYWluOk9GSTVOM2xqLz9vYmZzcGFyYW09JnByb3RvcGFyYW09NVktdjVZV042TFM1NUwyXzU1U282WmlfNlllTTVMcVI2YWFaNXJpdjVwYXc1WXFnNVoyaDVwZWw1cHlzJmdyb3VwPTVwYXc1WXFnNVoyaA
//ssr://ZGppdHcyYWlpLnNoaWppZW5hbWVoYW8ubWw6NTAwMDphdXRoX2FlczEyOF9tZDU6Y2hhY2hhMjAtaWV0Zjp0bHMxLjJfdGlja2V0X2F1dGg6T1VzNFFUWngvP29iZnNwYXJhbT1NRGN6TVRBeU1TNXFaQzVvYXcmcHJvdG9wYXJhbT1NakU2UVdoS1NEWTNTZyZyZW1hcmtzPWQzZDNMbXRoYjNCMUxuUnJJQzBnNVktdzVybS1JQzBnNXJPbzVZYU02STYzNWI2WDVwdTA1YVc5NUwyVDZhcU0mZ3JvdXA9NVktdzVybS0
//        """.trimIndent()

        val str =shareSSR!!.queryAllUrls().joinToString("\n")
        log.debug("str...$str")

        return Base64.getEncoder().encodeToString(str.toByteArray())
    }
}