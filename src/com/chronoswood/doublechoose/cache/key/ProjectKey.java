package com.chronoswood.doublechoose.cache.key;

import com.chronoswood.doublechoose.cache.AbstractBasePrefix;

public class ProjectKey extends AbstractBasePrefix {

    //1小时过期
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60;
    public static final String STUDENT_KEY_PREFIX = "project_";

    protected ProjectKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static ProjectKey projectKeyPrefix = new ProjectKey(TOKEN_EXPIRE_TIME, STUDENT_KEY_PREFIX);

}
