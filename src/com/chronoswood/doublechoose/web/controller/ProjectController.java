package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
@CrossOrigin("*")
public class ProjectController {
    @Autowired
    private ProjectsService projectsService;

    @RequestMapping("/list")
    Result getProjectList(@RequestParam("offset") int offset, @RequestParam("amount") int amount){
        if(offset<0 || amount<0){
            return new Result<>(Message.BIND_ERROR, null);
        }
        return new Result<>(Message.SUCCESS, projectsService.showProjects(offset, amount));
    }

    @RequestMapping("list/{directorName}")
    Result getProjectList(@RequestParam("directorName") String directorName, @RequestParam("offset") int offset, @RequestParam("amount") int amount){
        if(offset<0 || amount<0){
            return new Result<>(Message.BIND_ERROR, null);
        }
        return new Result<>(Message.SUCCESS, projectsService.queryProjectByDirectorUserName(directorName, offset, amount));
    }
}
