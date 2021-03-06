package com.jiangli.api;

import com.jiangli.jtest.core.RepeatFixedDuration;
import org.junit.Test;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jiangli
 * @date 2018/5/24 14:48
 */
public class CommonTest extends BaseTest  {
    long x;
    AtomicLong y=new AtomicLong();

    @RepeatFixedDuration
    @Test
    public void test_() {
        x++;
    }

    @RepeatFixedDuration
    @Test
    public void test_2() {
        y.incrementAndGet();
    }

    @Test
    public void test_base65() {
        String s = Base64.getEncoder().encodeToString("123456".getBytes());
        System.out.println(s);
    }


}
