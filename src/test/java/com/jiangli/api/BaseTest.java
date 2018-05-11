package com.jiangli.api;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jiangli
 * @date 2018/5/11 10:30
 */
@RunWith(SpringRunner.class)
@PropertySource({
        "classpath:test.properties"
})
@SpringBootTest
@ActiveProfiles("test")
public class BaseTest implements EnvironmentAware{
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    //-Dspring.profiles.active=test
    @Override
    public void setEnvironment(Environment environment) {
        log.warn("setEnvironment before:{}",environment);
        //StandardEnvironment standardEnvironment = (StandardEnvironment) environment;
        //standardEnvironment.addActiveProfile("test");
        log.warn("setEnvironment after:{}",environment);
    }
}
