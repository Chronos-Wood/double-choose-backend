package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import com.chronoswood.doublechoose.model.Project;
import com.chronoswood.doublechoose.model.Projects;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProjectsServiceImplTest {

    @Autowired public ProjectsService projectsService;
    @Test
    public void queryProjectByName() {
        Project project = projectsService.queryProjectByName("");
        log.info("test result:{}", project);
    }

    @Test
    public void addProject() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void showProjects() {
    }
}