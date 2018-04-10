package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.PeriodDao;
import com.chronoswood.doublechoose.model.Period;
import com.chronoswood.doublechoose.service.PeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PeriodServiceImpl implements PeriodService {
    @Autowired
    private PeriodDao periodDao;

    @Override
    public Period getLatestPeriod() {
        try{
            return periodDao.queryLatestPeriod();
        }catch (Exception e){
            log.error("无法获取当前时间段信息",e);
        }
        return null;
    }
}
