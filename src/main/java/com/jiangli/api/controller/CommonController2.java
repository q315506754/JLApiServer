package com.jiangli.api.controller;

import com.jiangli.api.utils.DiffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * @author Jiangli
 * @date 2020/8/6 17:12
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController2 extends BaseController{
    /**
     http://localhost:8010/diff?oldFile=http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico&newFile=http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png
     http://118.25.100.74:8010/diff?oldFile=http://file.g2s.cn/zhs_yanfa_150820/ablecommons/202008/1f698286874c435ab1ba3ee64820987c.ico&newFile=http://image.g2s.cn/zhs_yanfa_150820/ablecommons/202008/7868d5954cac46b4a48210278e2d5f91.png
     */
    @RequestMapping("/diff")
    public void diff(
            HttpServletRequest req
            , HttpServletResponse res
            , @RequestParam(name="oldFile")String oldFile
            , @RequestParam(name="newFile")String newFile
    ){
        File diff = DiffUtil.diff(oldFile, newFile);
        log.info("查分over");
        downFile(diff.getName(),diff,res);
    }

    private void downFile(String name,File file, HttpServletResponse response) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream output = null;
        try {
            log.info("downLoadFileStart:" + file);
            String fullName = file.getName();
            response.setContentType("application/octet-stream; charset=UTF-8");
            String ext = fullName.substring(fullName.lastIndexOf("."));
            //String name = fullName;
            name = name + ext;
            name = URLDecoder.decode(name, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + new String(name.getBytes("GBK"), "ISO8859-1") + "\"");

            bis = new BufferedInputStream(new FileInputStream(file));
            output = response.getOutputStream();
            bos = new BufferedOutputStream(output);
            log.info("downLoadFileCopyStream:" + name);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                log.info("downLoadFileCopyStreamDetail:" + bytesRead);
                bos.write(buff, 0, bytesRead);
            }
            log.info("downLoadFileEnd:" + name);
        } catch (Exception e) {
            log.error("downLoadFileError:" + file + ":error:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
