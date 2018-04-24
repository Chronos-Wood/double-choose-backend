package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.AssignmentDao;
import com.chronoswood.doublechoose.dao.WillDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Will;
import com.chronoswood.doublechoose.service.AssignmentService;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentDao assignmentDao;
    @Autowired
    private WillDao willDao;

    @Override
    @SuppressWarnings("unchecked")
    public int syncResult() {
        int offset = 0;
        int amount = 100;
        int acc = 0;

        List<Will> result = Collections.EMPTY_LIST;
        while(true){
            result = willDao.queryAcceptedWill(offset, amount);
            if(result.isEmpty()){
                break;
            }
            acc += assignmentDao.insertOrUpdate(result);
            offset += result.size();
        }

        return acc;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Will> queryAssignment(int offset, int amount) {
        try{
            val result = assignmentDao.queryAssignment(offset, amount);
            return Optional.ofNullable(result).orElse(Collections.EMPTY_LIST);
        }catch (Exception e){
            log.error("", e);
            throw new BizException("查询双选结果失败");
        }
    }
}
