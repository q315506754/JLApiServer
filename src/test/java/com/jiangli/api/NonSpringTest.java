package com.jiangli.api;

import com.jiangli.jtest.core.StatisticsJunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Base64;

/**
 * @author Jiangli
 * @date 2018/5/24 14:48
 */
@RunWith(StatisticsJunitRunner.class)
public class NonSpringTest   {
    @Test
    public void test_base65() {
        String s = Base64.getEncoder().encodeToString("123456".getBytes());
        System.out.println(s);
    }

}
