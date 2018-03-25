package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.AccountDO;
import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signin")
    public Result<Boolean> doLogin(@Valid AccountVO accountVO, HttpServletResponse response) {
        Message message = accountService.login(accountVO, response);
        if(message.equals(Message.SUCCESS)) {
            return Result.success(true);
        }
        return Result.error(message);
    }

    @PostMapping("/signup")
    public Result<Boolean> singnup(@Valid AccountVO accountVO) {
        Message message =  accountService.register(accountVO);
        if(message.equals(Message.SUCCESS)) {
            return Result.success(true);
        }
        return Result.error(message);
    }

    @PutMapping("/operation")
    public AccountDO testOperation(AccountDO account) {
        return account;
    }
}
