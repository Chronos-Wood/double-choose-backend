package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.cache.key.DirectorKey;
import com.chronoswood.doublechoose.dao.DirectorDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Director;
import com.chronoswood.doublechoose.service.DirectorService;
import com.chronoswood.doublechoose.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    private DirectorDao directorDao;

    private RedisService redisService;

    public DirectorServiceImpl(DirectorDao directorDao, RedisService redisService) {
        this.directorDao = directorDao;
        this.redisService = redisService;
    }

    @Override
    public int addDirector(Director director) {
        return directorDao.addDirector(director);
    }

    @Override
    public Director queryDirectorByUsername(String userName) {

        Director result = null;
        try{
            //先查缓存
            result = redisService.get(DirectorKey.directorKeyPrefix, userName, Director.class);
            if (result != null) {
                return result;
            }
            result = directorDao.getDirectorByUsername(userName);
        }catch (Exception e){
            log.error("查询教员信息失败",e);
            throw new BizException("查询教员信息失败");
        }
        if(result == null){
            throw new BizException("无法查询到相关教员信息");
        }
        //入缓存
        redisService.set(DirectorKey.directorKeyPrefix, userName, result);
        return result;
    }
}
