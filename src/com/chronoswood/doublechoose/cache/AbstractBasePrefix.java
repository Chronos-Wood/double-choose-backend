package com.chronoswood.doublechoose.cache;

public class AbstractBasePrefix implements KeyPrefix {

    private static final Integer NEVER_EXPIRE = -1;
    private Integer expireSeconds;

    private String prefix;

    protected AbstractBasePrefix(Integer expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    protected AbstractBasePrefix(String prefix){
        this(NEVER_EXPIRE, prefix);
    }


    public int expireSeconds() {
        return expireSeconds;
    }


    public String getPrefix() {
        return getClass().getSimpleName() + ":" + prefix;
    }
}
