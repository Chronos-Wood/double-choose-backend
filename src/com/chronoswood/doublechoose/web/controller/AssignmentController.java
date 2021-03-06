package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.model.Role;
import com.chronoswood.doublechoose.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/result")
@CrossOrigin("*")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(path = "/sync")
    public Result<?> sync(AccountVO accountVO){
        if(accountVO.getRole() != Role.ADMIN.getCode()){
            return new Result<>(Message.NO_PERMISSION,null);
        }
        return new Result<>(Message.SUCCESS, assignmentService.syncResult());
    }

    @RequestMapping(path = "result")
    public Result<?> result(@RequestParam("offset") int offset, @RequestParam("amount") int amount){
        return new Result<>(Message.SUCCESS, assignmentService.queryAssignment(offset, amount));
    }
}
