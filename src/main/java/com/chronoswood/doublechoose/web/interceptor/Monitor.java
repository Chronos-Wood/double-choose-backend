package com.chronoswood.doublechoose.web.interceptor;

import com.chronoswood.doublechoose.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.google.common.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO reimplementate in filter
public class Monitor implements HandlerInterceptor {

    private static final Logger PARAMS_LOGGER = LoggerFactory.getLogger(Monitor.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(Monitor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(StringUtils.isEmpty(request.getHeader("UserName"))
                || StringUtils.isEmpty(request.getHeader("Role"))){
            LOGGER.error("非法的请求，IP:\'{}\'", request.getRemoteAddr());
            response.sendError(500);
            return false;
        }


        MDC.put("userName", request.getHeader("UserName"));
        MDC.put("role", Role.getRole(Integer.valueOf(request.getHeader("Role"))).getDescription());
        MDC.put("request", CharStreams.toString(request.getReader()));

        return true;
    }
}
