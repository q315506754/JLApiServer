package com.jiangli.api.xxl;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiangli
 * @date 2018/3/13 9:51
 */
@Component
@JobHander("testJob")
public class PushDailySentenceJob extends IJobHandler {

    public String getCmd(String[] params,int idx,String def) throws Exception {
        if (params != null) {
            if (idx<params.length) {
                return params[idx].trim();
            }
        }
        return def;
    }

    public boolean getBooleanInCmd(String cmd, String c) throws Exception {
        return cmd.toLowerCase().contains(c.toLowerCase());
    }

    public Date parseDate(String def) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(def);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        XxlJobLogger.log("推送——每日一语——开始,当前时间："+sdf.format(new Date()));

        //e.g. 2018-03-14
        String pushDateStr = getCmd(params, 0, null);
        Date pushDate = parseDate(pushDateStr);
        XxlJobLogger.log("传入时间："+pushDateStr);
        XxlJobLogger.log("传入时间："+pushDate);

        //e.g. ai
        String targetUsers = getCmd(params, 1, "ai");
        XxlJobLogger.log("推送端："+targetUsers);
        boolean pushAndroid = getBooleanInCmd(targetUsers,"a");
        boolean pushIOS = getBooleanInCmd(targetUsers,"i");
        XxlJobLogger.log("推Android："+pushAndroid);
        XxlJobLogger.log("推ios："+pushIOS);

        Date date = new Date();
        if (pushDate!=null) {
            date = pushDate;
            XxlJobLogger.log("采用传入时间查询："+sdf.format(date));
        } else {
            XxlJobLogger.log("采用系统时间查询："+sdf.format(date));
        }

        try {
            XxlJobLogger.log("开始查询今日数据...");

            XxlJobLogger.log("推送——每日一语——结束...");
        } catch (Exception e) {
            XxlJobLogger.log(("推送——每日一语——异常..."+getExceptionContent(e)));
            return ReturnT.FAIL;
        }

        //success
        //fail
        return ReturnT.SUCCESS;
    }



    //获取红色字体的异常信息
    public static String getExceptionContent(Object obj){

        String exceptionInfo="\n<span style='color:red;'>exception:</span>"+obj;
        exceptionInfo+="\n";
        return exceptionInfo;
    }
}
