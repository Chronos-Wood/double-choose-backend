package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Will;

import java.util.List;

public interface AssignmentService {
    int syncResult();
    List<Will> queryAssignment(int offset, int amount);
}
