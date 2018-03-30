package com.chronoswood.doublechoose.configuration;

import com.chronoswood.doublechoose.web.AccountArgumentResovler;
import com.chronoswood.doublechoose.web.interceptor.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired  private AccountArgumentResovler resovler;
    @Autowired  private Monitor monitor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(monitor).addPathPatterns("/api/user/**").excludePathPatterns("/api/user/signin/**","/api/user/signup/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resovler);
    }
}
