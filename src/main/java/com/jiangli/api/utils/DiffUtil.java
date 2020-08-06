package com.jiangli.api.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author Jiangli
 * @date 2020/8/6 16:42
 */
@Slf4j
public class DiffUtil {
    /**
     * 下载文件
     http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico
     http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png

     */
    public static File downloadNet(String fileUrl) {
        try {
            fileUrl = fileUrl .trim();

            String suffix = fileUrl.substring(fileUrl.lastIndexOf("."));
            File outFile = File.createTempFile("temp"+getUUID() , ""+suffix);
            log.info("临时文件已生成:" + outFile);

            // 下载网络文件
            int byteread = 0;
            URL url = new URL(fileUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(outFile);
            log.info("开始下载文件:" + fileUrl);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            fs.close();
            return outFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String fileUrl = "http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico";
        String fileUrl2 = "     http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png";
        //System.out.println(downloadNet(fileUrl));
        System.out.println(diff(fileUrl,fileUrl2));
    }

    public static File diff(String oldFile, String newFile) {
        return diff(downloadNet(oldFile),downloadNet(newFile));
    }
    public static File diff(File oldFile, File newFile) {
        String oldFilePath = oldFile.getAbsolutePath();
        String suffix = oldFilePath.substring(oldFilePath.lastIndexOf("."));

        //File patchFile = new File(getDiffDir() + getUUID() + suffix);
        File patchFile = null;
        try {
            patchFile = File.createTempFile("diff"+getUUID() , ""+suffix);

            String command = new StringBuilder("/root/bsdiff-4.3/bsdiff").append(" ")
                    .append(oldFilePath).append(" ").append(newFile.getAbsolutePath()).append(" ").append(patchFile.getAbsolutePath()).toString();
            log.info("差分包处理中command:" + command);
            Process process = Runtime.getRuntime().exec(command);// 执行命令

            new ProcessClearStream(process.getInputStream(), "INFO").start();
            new ProcessClearStream(process.getErrorStream(), "ERROR").start();
            log.info("差分包处理结束..." + command);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("差分文件..." + patchFile);

        return patchFile;
    }

    public static String getUUID() {
        StringBuffer sbrUuid = new StringBuffer();
        String uuid = UUID.randomUUID().toString();// 获取随机唯一标识符
        // 去掉标识符中的"-"
        sbrUuid.append(uuid.substring(0, 8)).append(uuid.substring(9, 13))
                .append(uuid.substring(14, 18)).append(uuid.substring(19, 23))
                .append(uuid.substring(24));
        return sbrUuid.toString();
    }
}
