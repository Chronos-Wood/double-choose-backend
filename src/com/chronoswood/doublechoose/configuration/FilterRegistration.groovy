package com.chronoswood.doublechoose.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class FilterRegistration
{
    @Bean
    @Order(1)
    FilterRegistrationBean getRequestCachingFilter(){
        return new FilterRegistrationBean(){{
            setFilter(new CachedFilter())
            addUrlPatterns('/*')
        }}
    }
}
class CachedFilter extends GenericFilterBean {
    private static final Logger PARAMS_LOGGER = LoggerFactory.getLogger("params")

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper ((HttpServletRequest) servletRequest)
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse)

        chain.doFilter(wrappedRequest, responseWrapper)

        def response = new String(responseWrapper.contentAsByteArray)
        PARAMS_LOGGER.info("##username：${MDC.get('userName')} ##role：${MDC.get('role')} ##url：${wrappedRequest.requestURI}\n[request]：${MDC.get('request')} \n[response]：$response")

        responseWrapper.copyBodyToResponse()

    }
}
