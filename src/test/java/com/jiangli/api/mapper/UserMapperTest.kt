package com.jiangli.api.mapper

import com.jiangli.api.BaseTest
import com.jiangli.jtest.core.RepeatFixedDuration
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

open class UserMapperTestKt: BaseTest() {
    @Autowired
    private val userMapper: UserMapper? = null

    @Test
    fun get() {
        val course = userMapper!!.queryByUserId(1L)
        println(course)
    }

    @Test
    @RepeatFixedDuration
    fun queryByUserId() {
        val course = userMapper!!.queryByUserId(1L)
    }
}