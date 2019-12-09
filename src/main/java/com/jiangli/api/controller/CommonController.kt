package com.jiangli.api.controller

import okhttp3.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
@RestController
@RequestMapping("/")
 class CommonController : BaseController() {
    val client = OkHttpClient.Builder()
            // 连接池最大空闲连接30，生存周期30秒(空闲30秒后将被释放)
            .connectionPool(ConnectionPool(30, 30, TimeUnit.SECONDS))
            // 连接超时，10秒，失败时重试，直到10秒仍未连接上，则连接失败
            .connectTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true)
            // 写超时，向服务端发送数据超时时间
            .writeTimeout(10, TimeUnit.SECONDS)
            // 读超时，从服务端读取数据超时时间
            .readTimeout(10, TimeUnit.SECONDS).build()


    /**
     * http://localhost:8010/redirectJson
     *


    http://localhost:8010/redirectJson?url=https%3A%2F%2Foapi.dingtalk.com%2Frobot%2Fsend%3Faccess_token%3Dba312a0d85bbb8dd63b58efb5496d8ae94e87b48f7ebd6dabf8f722c8cd799d1



     */
    @RequestMapping("/redirectJson")
    fun redirectJson(
        @RequestParam  url:String
        ,@org.springframework.web.bind.annotation.RequestBody(required = false)  content:String?
    ): String {

        println("enter redirectJson..$url")

        val request = Request
                .Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36")
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content?:"")).build()

        try {
            val execute = client.newCall(request).execute()
            println(execute)
            return execute.body().string()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return "NO_RES_TXT"
    }
}