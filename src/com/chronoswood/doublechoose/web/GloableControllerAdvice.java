package com.chronoswood.doublechoose.web;

import com.chronoswood.doublechoose.cache.key.AccountKey;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.AccountDO;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GloableControllerAdvice {

    private AccountService accountService;

    public GloableControllerAdvice(AccountService accountService) {
        this.accountService = accountService;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e) {
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            StringBuilder sb = new StringBuilder("");
            if (errors.size() > 0) {
                for (ObjectError error: errors) {
                    sb.append(error.getDefaultMessage());
                    sb.append("\n");
                }
            }
            return Result.error(Message.BIND_ERROR.bindArgs(sb.toString()));
        }
        if (e instanceof BizException) {
            BizException be = (BizException) e;
            return Result.error(be.getErrMsg());
        }
        log.error("error occurred: {}", e);
        return Result.error(Message.SERVER_ERROR);
    }
}
