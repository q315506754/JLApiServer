package com.jiangli.api.mapper

import com.jiangli.api.BaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

open class ShareSSRMapperTest: BaseTest() {
    @Autowired
    private val userMapper: ShareSSRMapper? = null

    @Test
    fun queryAll() {
        val course = userMapper!!.queryAll()
        println(course)
    }

}