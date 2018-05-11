package com.jiangli.api.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiangli
 * @date 2018/5/11 9:59
 */
@Data
@ConfigurationProperties(prefix = "mysql.common")
public class ApplicationConfig {
    private String url;//
    private String username;//
    private String password;//
}
