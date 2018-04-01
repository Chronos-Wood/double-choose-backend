package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Director;

public interface DirectorService {

    int addDirector(Director director);

    /**
     * 通过账号名查询教员详细信息
     * @param userName 账号名
     * @return null如果查询不到相关信息，否则返回Director实例
     */
    Director queryDirectorByUsername(String userName);
}
