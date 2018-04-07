package com.chronoswood.doublechoose.web.controller;


import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Director;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.service.DirectorService;
import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/teacher")
@CrossOrigin("*")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping("list")
    public Result<?> list(@RequestParam("offset") int offset, @RequestParam("amount") int amount){
        if(offset<0||amount<0){
            return new Result<>(Message.BIND_ERROR, null);
        }
        return new Result<>(Message.SUCCESS, directorService.queryDirector(offset, amount));
    }

    @PostMapping("update")
    public Result<?> update(AccountVO accountVO, Director director){
        if(!Objects.equal(accountVO.getUserName(), director.getUserName())){
            throw new BizException(Message.USER_NOT_EXIST);
        }

        directorService.updateDirectorInfo(director);
        return new Result<>(Message.SUCCESS, null);
    }
    @RequestMapping(path = "/detail")
    public Result detail(String userName){
        return Result.success(directorService.queryDirectorByUsername(userName));
    }
}
