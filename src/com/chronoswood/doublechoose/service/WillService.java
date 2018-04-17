package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Will;
import com.chronoswood.doublechoose.model.WillDto;

import java.util.List;
import java.util.Map;

public interface WillService {
    int submitWills(String studentUserName, List<String> projectIds);
    Map<String, List<Will>> queryWillByStudentUserName(String studentUserName);
    int acceptWills(String directorUserName, List<String> willIds);
    List<WillDto> queryWill(String directorUserName, String projectId, int offset, int amount);
    List<Will> getAcceptedWillsByProjectId(String projectId);
}
