package com.chronoswood.doublechoose.service;


import com.chronoswood.doublechoose.model.AccountDO;
import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Message;

import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    Message login(AccountVO accountVO, HttpServletResponse response);

    AccountDO getUserByToken(String token, HttpServletResponse response);
//
    Message register(AccountVO accountVO);
}
