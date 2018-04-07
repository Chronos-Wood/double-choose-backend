package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.cache.key.StudentKey;
import com.chronoswood.doublechoose.cache.key.ProjectKey;
import com.chronoswood.doublechoose.dao.ProjectsDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Project;
import com.chronoswood.doublechoose.model.Projects;
import com.chronoswood.doublechoose.service.ProjectsService;
import com.chronoswood.doublechoose.service.RedisService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProjectsDao projectsDao;

    @Override
    public Project queryProjectByName(String Name) {

        Project result = null;
        try{
            //先查缓存
            result = redisService.get(StudentKey.studentKeyPrefix, Name, Project.class);
            if (result != null) {
                return result;
            }
            result =  projectsDao.queryProjectByName(Name);
        }catch (Exception e){
            log.error("查询项目信息失败",e);
            throw new BizException("查询项目信息失败");
        }
        if(result==null){
            throw new BizException("无法查询到相关项目信息");
        }
        //入缓存
        redisService.set(ProjectKey.projectKeyPrefix, Name, result);
        return result;
    }

    @Override
    public int addProject(Project project) {
        return projectsDao.addProject(project);
    }

    @Override
    public int updateProject(Project project) {
        if(!Objects.equals(MDC.get("userName"), project.getName())){
            throw new BizException("无访问权限");
        }
        try{
            int affectedRows =  projectsDao.updateProjectInfo(project);
            if (affectedRows > 0) {
                //删除缓存
                redisService.remove(ProjectKey.projectKeyPrefix, project.getName());
            }
            return affectedRows;
        }catch (Exception e){
            log.error("更新学生信息失败",e);
            throw new BizException("更新学生信息失败");
        }
    }

    //Show the list of professor
    public Projects showProjects() {
        Projects result = null;
        try{
            //先查缓存
            result = redisService.get(ProjectKey.projectKeyPrefix, "projects", Projects.class);
            if (result != null) {
                return result;
            }
            result = projectsDao.queryProjects();
        }catch (Exception e){
            log.error("显示项目信息失败",e);
            throw new BizException("显示项目信息失败");
        }
        if(result==null){
            throw new BizException("无法查询到项目相关信息");
        }
        //入缓存
        redisService.set(StudentKey.studentKeyPrefix, "projects", result);
        return result;
    }
    //Show the information of project

    //Show the information students
}
