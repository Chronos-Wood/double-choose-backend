package com.chronoswood.doublechoose.web.interceptor

import com.chronoswood.doublechoose.cache.key.AccountKey
import com.chronoswood.doublechoose.model.AccountDO
import com.chronoswood.doublechoose.model.Message
import com.chronoswood.doublechoose.model.Result
import com.chronoswood.doublechoose.service.AccountService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.CharStreams
import groovy.util.logging.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Component
class PrevilegeChecker implements HandlerInterceptor {

    @Autowired
    private AccountService accountService

    @Autowired ObjectMapper objectMapper;

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(AccountKey.TOKEN_NAME)?
                request.getHeader(AccountKey.TOKEN_NAME): "";
        Enumeration<String> headers = request.getHeaderNames();
        AccountDO accountDO = accountService.getUserByToken(token, response)
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,tk");

        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        if(accountDO == null){
            response.setCharacterEncoding("UTF-8");
            response.writer.print(objectMapper.writeValueAsString(new Result(Message.SESSION_EXPIRED, null)))
            return false
        }

        MDC.put("userName", accountDO.userName)
        MDC.put("role", "$accountDO.role")
        return true
    }
}
