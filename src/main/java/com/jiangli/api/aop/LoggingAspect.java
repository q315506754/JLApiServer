package com.jiangli.api.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @date 2018-01-23 18:43
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // 进入日志计数器(只对进入方法时调用的日志计数)
    private static final AtomicLong logCounter = new AtomicLong();

    /**
     * 数据访问层日志切面
     */
    @Pointcut("execution(public * com.jiangli.api.mapper..*.*(..))")
    public void daoAspect() {}

    /**
     * 业务层日志切面
     */
    @Pointcut("execution(public * com.jiangli.api.service..*.*(..))")
    public void serviceAspect() {}

//    Dubbo 与 Spring AOP 有冲突，官方目前尚未解决，暂时不使用这里的AOP日志
//    /**
//     * 开放接口日志切面
//     */
//    @Pointcut("execution(public * com.zhihuishu.aries.*.openapi..*.*(..))")
//    public void openapiAspect() {}

    /**
     * 前置事件，记录每个方法进入的事件
     * @param point
     */
    @Before("daoAspect() || serviceAspect()")
    public void before(JoinPoint point) {
        //System.out.println("before:"+point);
        // 只有对LoggingAspect类开放DEBUG级别时，才输出前置事件日志，通常用于调试使用
        if (log.isDebugEnabled()) {
            Signature signature = point.getSignature();
            log.debug("AOP_BEFORE: {}#{}(),args={},number={}",
                    signature.getDeclaringTypeName(),
                    signature.getName(),
                    ArrayUtils.getLength(point.getArgs()),
                    logCounter.incrementAndGet());
        }
    }

    /**
     * 后置事件，记录每个方法结束的事件
     * @param point
     */
    @After("daoAspect() || serviceAspect()")
    public void after(JoinPoint point) {
        //System.out.println("after:"+point);
        if (log.isDebugEnabled()) {
            Signature signature = point.getSignature();
            log.debug("AOP_AFTER: {}#{}(),args={}",
                    signature.getDeclaringTypeName(),
                    signature.getName(),
                    String.valueOf(ArrayUtils.getLength(point.getArgs())));
        }
    }

    /**
     * 抛出异常时，记录日志
     * @param point
     * @param ex
     */
    @AfterThrowing(pointcut = "daoAspect() || serviceAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint point, Throwable ex) {
        // 记录异常时的类名、方法名、参数列表及异常堆栈信息
        Signature signature = point.getSignature();
        MDC.put("_class", signature.getDeclaringTypeName());
        MDC.put("_method", signature.getName());
        if (!ArrayUtils.isEmpty(point.getArgs())) {
            int i = 0;
            for (Object arg : point.getArgs()) {
                MDC.put("_arg" + (i ++), arg != null ? arg.toString() : null);
            }
        }
        log.error("AOP_AFTER_THROWING", ex);
        MDC.remove("_class");
        MDC.remove("_method");
        if (!ArrayUtils.isEmpty(point.getArgs())) {
            int i = 0;
            for (Object arg : point.getArgs()) {
                MDC.remove("_arg" + (i++));
            }
        }
    }

}
