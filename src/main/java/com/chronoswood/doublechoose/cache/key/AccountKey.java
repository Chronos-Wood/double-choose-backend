package com.chronoswood.doublechoose.cache.key;

import com.chronoswood.doublechoose.cache.AbstractBasePrefix;

public class AccountKey extends AbstractBasePrefix {

    //过期时间7天
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7;

    public static final String TOKEN_NAME = "tk";
    private AccountKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccountKey tokenKey = new AccountKey(TOKEN_EXPIRE_TIME, TOKEN_NAME);
}
