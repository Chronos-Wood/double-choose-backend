package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.PeriodDao;
import com.chronoswood.doublechoose.dao.WillDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Period;
import com.chronoswood.doublechoose.model.Will;
import com.chronoswood.doublechoose.service.WillService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WillServiceImpl implements WillService {
    @Autowired
    private WillDao willDao;
    @Autowired
    private PeriodDao periodDao;

    @Override
    public int submitWills(@NonNull String studentUserName,@NonNull List<String> projectIds) {
        try{
            Period period = periodDao.queryLatestPeriod();
            if(period==null){
                throw new BizException("不在可以提交志愿的时间内");
            }
            return willDao.storeWill(studentUserName, String.valueOf(period.getId()), projectIds);
        }catch (Exception e){
            log.error("无法提交志愿", e);
            throw new BizException("无法提交志愿");
        }
    }

    @Override
    public List<Will> queryWillByStudentUserName(String studentUserName) {
        try{
            return willDao.queryAcceptedWillsByStudentUserName(studentUserName);
        }catch (Exception e){
            log.error("无法查询提交记录", e);
            throw new BizException("无法查询提交记录");
        }
    }

    @Override
    public int acceptWills(String directorUserName, List<String> willIds) {
        try{
            return willDao.acceptWill(directorUserName, willIds);
        }catch (Exception e){
            log.error("接受志愿失败",e);
            throw new BizException("接受志愿失败");
        }
    }

    @Override
    public List<Will> queryWill(String directorUserName, String projectId, int offset, int amount) {
        try{
            return willDao.queryWillByDirectorUsernameAndProjectId(projectId, directorUserName, offset, amount);
        }catch (Exception e){
            log.error("查询收到的志愿失败", e);
            throw new BizException("查询收到的志愿失败");
        }
    }
}
