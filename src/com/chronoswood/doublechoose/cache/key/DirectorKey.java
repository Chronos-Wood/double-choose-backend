package com.chronoswood.doublechoose.cache.key;

import com.chronoswood.doublechoose.cache.AbstractBasePrefix;

public class DirectorKey extends AbstractBasePrefix {

    //2小时过期
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60 * 2;
    public static final String DIRECTOR_KEY_PREFIX = "director_";

    protected DirectorKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static DirectorKey directorKeyPrefix = new DirectorKey(TOKEN_EXPIRE_TIME, DIRECTOR_KEY_PREFIX);
}