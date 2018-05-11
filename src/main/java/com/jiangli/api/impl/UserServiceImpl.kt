package com.jiangli.api.impl

import com.jiangli.api.mapper.UserMapper
import com.jiangli.api.model.User
import com.jiangli.api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:39
 */
/**
 * @author Jiangli
 * @date 2018/3/15 17:42
 */
@Service
open class UserServiceImpl : BaseServiceImpl<Any, Any>(), UserService {

    @Autowired
    private val userMapper: UserMapper? = null

    override fun queryByUserId(userId: Long?): User {
        return userMapper!!.queryByUserId(userId)
    }
}