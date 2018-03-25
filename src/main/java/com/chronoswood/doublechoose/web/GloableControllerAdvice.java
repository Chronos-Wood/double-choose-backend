package com.chronoswood.doublechoose.web;

import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GloableControllerAdvice {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e) {
        log.error("error occurred: {}", e);
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
        return Result.error(Message.SERVER_ERROR);
    }
}
