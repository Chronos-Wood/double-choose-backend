package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.AssignmentDao;
import com.chronoswood.doublechoose.dao.WillDao;
import com.chronoswood.doublechoose.model.Will;
import com.chronoswood.doublechoose.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
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
        do{
            result = willDao.queryAcceptedWill(offset, amount);
            acc += assignmentDao.insertOrUpdate(result);
            offset += result.size();
        }while(!result.isEmpty());
        return acc;
    }
}
