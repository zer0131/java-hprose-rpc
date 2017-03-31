package com.ryan.hprose.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志切面类, spring 3.x后推荐使用方式
 *
 * @author zhangenrui
 */
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * 前置通知
     * @param joinPoint 连接点
     */
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("before");
    }

    /**
     * 后置通知
     * @param joinPoint 连接点
     * @param obj 返回对象
     */
    public void afterReturning(JoinPoint joinPoint, Object obj) {
        logger.info("after returning");
    }

    /**
     * 最终通知
     * @param joinPoint 连接点
     */
    public void after(JoinPoint joinPoint) {
        logger.info("after");
    }

    /**
     * 异常通知
     * @param exception 异常对象
     */
    public void throwAdvice(Exception exception) {
        logger.error(exception.getMessage());
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint 类
     */
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info(objectMapper.writeValueAsString(proceedingJoinPoint.proceed()));
    }

}
