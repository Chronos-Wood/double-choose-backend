package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Will;

import java.util.List;

public interface WillService {
    int submitWills(String studentUserName, List<String> projectIds);
    List<Will> queryWillByStudentUserName(String studentUserName);
    int acceptWills(String directorUserName, List<String> willIds);
    List<Will> getAcceptedWillsByProjectId(String projectId);
    List<Will> queryWill(String directorUserName, String projectId, int offset, int amount);
}
