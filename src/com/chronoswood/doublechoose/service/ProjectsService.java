package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Projects;
import com.chronoswood.doublechoose.model.Project;

import java.util.List;

public interface ProjectsService {
    /**
     * 返回项目的详细信息
     * @param Name 项目名
     * @return 返回Project
     */
    Project queryProjectByName(String Name);

    /**
     * 返回所有项目信息
     * @param
     * @param offset
     *@param amount @return null如果查询不到相关信息，否则返回Projects实例
     */
    Projects showProjects(int offset, int amount);

    /**
     * 插入项目
     * @param project 要插入的学生
     * @return 受影响的行数 大于0成功
     */
    int addProject(Project project);
    /**
     * 更新project的信息，这个方法会更新project中的所有非空数据项
     * @param project 插入project的信息
     * @return 受影响的行数
     */
    int updateProject(Project project);

    List<Project> queryProjectByDirectorUserName(String directorUserName, int offset, int amount);

}
