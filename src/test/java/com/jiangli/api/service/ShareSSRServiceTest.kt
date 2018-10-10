package com.jiangli.api.service

import com.jiangli.api.BaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

open class ShareSSRServiceTest : BaseTest() {
    @Autowired
    private val userMapper: ShareSSRService? = null

    @Test
    fun queryAll() {
        val course = userMapper!!.queryAllUrls()
        println(course)
    }

}