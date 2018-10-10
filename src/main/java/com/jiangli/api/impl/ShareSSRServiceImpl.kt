package com.jiangli.api.impl

import com.jiangli.api.mapper.ShareSSRMapper
import com.jiangli.api.service.ShareSSRService
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
open class ShareSSRServiceImpl : BaseServiceImpl<Any, Any>(), ShareSSRService {

    @Autowired
    private val userMapper: ShareSSRMapper? = null

    override fun queryAllUrls(): MutableList<String> {
        val queryAll = userMapper!!.queryAll()

        val ret= mutableListOf<String>()

        queryAll.forEach {
            if (it.url!=null) {
                val fix = "://"
                val realSSR = "ssr"+fix + it.url.substring(it.url.indexOf(fix)+fix.length)
                ret.add(realSSR)
            }
        }

        return ret

    }

}