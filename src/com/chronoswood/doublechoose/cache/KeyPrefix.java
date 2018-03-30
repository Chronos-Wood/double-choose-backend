package com.chronoswood.doublechoose.cache;

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
