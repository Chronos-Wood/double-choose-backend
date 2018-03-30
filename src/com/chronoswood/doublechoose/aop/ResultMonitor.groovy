package com.chronoswood.doublechoose.aop;

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonOutput
import groovy.util.logging.Slf4j;
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
@Slf4j
class ResultMonitor {
    private static final Logger PARAMS_LOGGER = LoggerFactory.getLogger("params");
    @Autowired
    private ObjectMapper objectMapper

    @Around("execution(* com.chronoswood.doublechoose.web.controller..*(..))")
    Object doAround(ProceedingJoinPoint joinPoint) throws Exception {
        def result = null
        try{
            result = joinPoint.proceed();
        } catch (e) {
            log.error('业务处理错误:\n', e);
            throw e
        }finally {
            def response = objectMapper.writeValueAsString(result)
            PARAMS_LOGGER.info("username：${MDC.get('userName')} role：${MDC.get('role')}\n[request]：${MDC.get('request')}\n[response]：$response")
        }
        result;
    }
}
