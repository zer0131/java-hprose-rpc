package com.ryan.hprose.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * spring aop拦截器
 *
 * @author zhangenrui
 */
public class HproseMethodInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HproseMethodInterceptor.class);

    public static final long TIME_THRESHOLD = 5000;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String className = invocation.getThis().getClass().getSimpleName();
        String methodName = invocation.getMethod().getName();
        String requestParam = Arrays.toString(invocation.getArguments());

        Slf4JStopWatch stopWatch = new Slf4JStopWatch();
        stopWatch.start();

        Object result = invocation.proceed();

        if (stopWatch.getElapsedTime() <= TIME_THRESHOLD) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(methodName).append(".success");

            stopWatch.stop(buffer.toString());
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append(methodName).append(".failure");

            stopWatch.stop(buffer.toString());
        }

        String responseParam = null;
        if (result != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            responseParam = objectMapper.writeValueAsString(result);
        }

        logger.info("request class: {}, request method: {}, request param: {}, response param: {}", className, methodName, requestParam, responseParam);
        return result;
    }
}
