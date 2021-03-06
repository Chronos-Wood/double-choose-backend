package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.*;
import com.chronoswood.doublechoose.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signin")
    public Result<TokenVO> doLogin(@Valid AccountVO accountVO, HttpServletResponse response) {
        return Result.success(accountService.login(accountVO, response));
    }

    @PostMapping("/signup")
    public Result<Boolean> singnup(@RequestBody @Valid SignUpVO signUpVO) {
        return Result.success(accountService.register(signUpVO));
    }

    @PutMapping("/operation")
    public AccountDO testOperation(AccountDO account) {
        return account;
    }
}
