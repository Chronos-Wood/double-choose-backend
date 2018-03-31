package com.chronoswood.doublechoose.service;


import com.chronoswood.doublechoose.model.*;

import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    TokenVO login(AccountVO accountVO, HttpServletResponse response);

    AccountDO getUserByToken(String token, HttpServletResponse response);
//
    boolean register(SignUpVO signUpVO);
}
