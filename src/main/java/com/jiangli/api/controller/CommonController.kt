package com.jiangli.api.controller

import com.jiangli.api.utils.DiffUtil
import com.jiangli.api.utils.IpAdrressUtil
import lombok.extern.slf4j.Slf4j
import okhttp3.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 11:36
 */
@Slf4j
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

    /**
     * http://localhost:8010/getIp
     * curl http://118.25.100.74:8010/getIp
     */
    @RequestMapping("/getIp")
    fun getIp(
            req:HttpServletRequest
    ): String {
        return IpAdrressUtil.getIpAddr(req)
    }

    /**
     http://localhost:8010/diff?oldFile=http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico&newFile=http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png
    http://118.25.100.74:8010/diff?oldFile=http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico&newFile=http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png
     */
    @RequestMapping("/diff")
    fun diff(
            req:HttpServletRequest
    ,res:HttpServletResponse
    ,@RequestParam(name="oldFile") oldFile:String
    ,@RequestParam(name="newFile") newFile:String
    ): String {
        val diff = DiffUtil.diff(oldFile, newFile)
        log.info("查分over")
        return diff.absolutePath
    }
}