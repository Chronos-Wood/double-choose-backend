package com.chronoswood.doublechoose.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
public class ResultMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultMonitor.class);
    private static final Logger PARAMS_LOGGER = LoggerFactory.getLogger("params");
    @Autowired
    private ObjectMapper objectMapper;

    @Around("execution(* com.chronoswood.doublechoose.web.controller..*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Exception {
        Object result = null;
        try{
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("业务处理错误： {}", throwable);
        }finally {
            PARAMS_LOGGER.info("username：{} role：{}\n[request]：{}\n[response]：{}",
                    MDC.get("userName"),
                    MDC.get("role"),
                    MDC.get("request"),
                    objectMapper.writeValueAsString(Optional.ofNullable(result).orElse("")));
        }
        return result;
    }
}
