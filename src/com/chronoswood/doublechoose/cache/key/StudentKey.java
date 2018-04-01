package com.chronoswood.doublechoose.cache.key;

import com.chronoswood.doublechoose.cache.AbstractBasePrefix;

public class StudentKey extends AbstractBasePrefix {

    //2小时过期
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60 * 2;
    public static final String STUDENT_KEY_PREFIX = "student_";

    protected StudentKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static StudentKey studentKeyPrefix = new StudentKey(TOKEN_EXPIRE_TIME, STUDENT_KEY_PREFIX);
}
