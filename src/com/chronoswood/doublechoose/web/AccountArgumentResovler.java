package com.chronoswood.doublechoose.web;

import com.chronoswood.doublechoose.cache.key.AccountKey;
import com.chronoswood.doublechoose.model.AccountDO;
import com.chronoswood.doublechoose.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccountArgumentResovler implements HandlerMethodArgumentResolver {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == AccountDO.class;
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (request == null) {
            return null;
        }
        String appToken = request.getHeader(AccountKey.TOKEN_NAME);
        if(!StringUtils.hasText(appToken)) {
            return null;
        }
        AccountDO account = accountService.getUserByToken(appToken, response);
        if (account == null) {
            return null;
        }
        return account;
    }
}
