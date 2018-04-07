package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.model.Role;
import com.chronoswood.doublechoose.service.WillService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/will")
@CrossOrigin("*")
public class WillController {
    @Autowired
    private WillService willService;

    @RequestMapping("/list/student")
    public Result getList(AccountVO accountVO) {
        return new Result<>(Message.SUCCESS, willService.queryWillByStudentUserName(accountVO.getUserName()));
    }

    @RequestMapping("/list/director")
    public Result getList(AccountVO accountVO, @RequestParam("projectId") String projectId, @RequestParam("offset") int offset, @RequestParam("amount") int amount) {
        if(StringUtils.isEmpty(projectId) || offset<0 || amount<=0){
            throw new BizException(Message.BIND_ERROR);
        }
        return new Result<>(Message.SUCCESS, willService.queryWill(accountVO.getUserName(), projectId, offset, amount));
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/choose")
    public Result choose(AccountVO accountVO, @RequestBody List<String> projectIds){
        if(accountVO.getRole() != Role.STUDENT.getCode()){
            throw new BizException(Message.NO_PERMISSION);
        }
        return new Result<>(Message.SUCCESS, willService.submitWills(accountVO.getUserName(), Optional.ofNullable(projectIds).orElse(Collections.EMPTY_LIST)));
    }

    @PostMapping("/accept")
    @SuppressWarnings("unchecked")
    public Result accept(AccountVO accountVO, @RequestBody List<String> willIds){
        if(accountVO.getRole() != Role.STAFF.getCode()){
            throw new BizException(Message.NO_PERMISSION);
        }
        return new Result<>(Message.SUCCESS, willService.acceptWills(accountVO.getUserName(), Optional.ofNullable(willIds).orElse(Collections.EMPTY_LIST)));
    }


}
