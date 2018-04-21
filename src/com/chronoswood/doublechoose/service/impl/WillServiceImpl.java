package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.PeriodDao;
import com.chronoswood.doublechoose.dao.ProjectsDao;
import com.chronoswood.doublechoose.dao.WillDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.*;
import com.chronoswood.doublechoose.service.*;
import com.google.common.collect.Sets;
import com.chronoswood.doublechoose.model.*;
import com.chronoswood.doublechoose.service.DirectorService;
import com.chronoswood.doublechoose.service.PeriodService;
import com.chronoswood.doublechoose.service.StudentService;
import com.chronoswood.doublechoose.service.WillService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WillServiceImpl implements WillService {
    @Autowired
    private WillDao willDao;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProjectsDao projectDao;

    @Override
    public int submitWills(@NonNull String studentUserName,@NonNull List<String> projectIds) {

        Period period = periodService.getLatestPeriod();
        Student student = studentService.queryStudentByUsername(studentUserName);
        if(period==null || period.getType()!= PeriodType.CHOOSE_PROJECT.getCode()){
            throw new BizException("不在可以提交志愿的时间内");
        }
        if(projectIds.size()!=3){
            throw new BizException("非法的志愿数量");
        }

        try{
            val wills = new ArrayList<Will>(3);
            for(int i=0; i<3; i++){
                val will = new Will();
                will.setPrecedence(i + 1);
                will.setStudentId(String.valueOf(student.getId()));
                will.setStudentName(studentUserName);
                will.setProjectId(projectIds.get(i));
                will.setPeriodId(period.getId() + "");
                wills.add(will);
            }

            return willDao.storeWill(wills);
        } catch (DuplicateKeyException e) {
            throw new BizException("无法重复提交志愿");
        } catch (Exception e){
            log.error("无法提交志愿", e);
            throw new BizException("无法提交志愿");
        }
    }

    @Override
    public Map<String, List<Will>> queryWillByStudentUserName(String studentUserName) {
        try{
            List<Will> willList =  willDao.queryWillsByStudentUserName(studentUserName);
            return willList.stream().collect(Collectors.groupingBy(Will::getPeriodId));
        }catch (Exception e){
            log.error("无法查询提交记录", e);
            throw new BizException("无法查询提交记录");
        }
    }

    @Override
    @Transactional
    public int acceptWills(@NonNull String directorUserName,@NonNull List<String> willIds) {

        Period period = periodService.getLatestPeriod();
        if(period==null || period.getType()!=PeriodType.CHOOSE_STUDENT.getCode()){
            throw new BizException("不在可以接受志愿的时间内");
        }

        if(Sets.newHashSet(willIds).size()>1){
            throw new BizException("一次只能操作一个课题");
        }

        try{
            Will will = willDao.queryWillById(willIds.size()>0?willIds.get(0):"");
            Project project = projectDao.queryProjectById(will.getPeriodId());
            List<Will> acceptedWill = willDao.queryAcceptedWillsByProjectId(will.getProjectId());

            if(project.getParticipantAmount() != null && acceptedWill.size()+willIds.size() > project.getParticipantAmount()){
                throw new BizException("超过容纳人数上限");
            }

            return willDao.acceptWill(directorUserName, willIds);
        }catch (Exception e){
            log.error("接受志愿失败",e);
            throw new BizException("接受志愿失败");
        }
    }

    @Override
    public List<Will> getAcceptedWillsByProjectId(String projectId) {
        try{
            return willDao.queryAcceptedWillsByProjectId(projectId);
        }catch (Exception e){
            log.error("查询课程：\'{}\'已选中志愿", projectId);
        }
        return null;
    }

    @Override
    public List<WillDto> queryWill(String directorUserName, String projectId, int offset, int amount) {
        try{
            return willDao.queryWillByDirectorUsernameAndProjectId(projectId, directorUserName, offset, amount);
        }catch (Exception e){
            log.error("查询收到的志愿失败", e);
            throw new BizException("查询收到的志愿失败");
        }
    }
}
