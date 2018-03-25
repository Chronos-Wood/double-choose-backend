package com.chronoswood.doublechoose.web.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void doLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/user/signin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("userName", "20131346055")
                .param("password", "def5e35d3d2a9e985e5bbb6b02465fd5")
                .param("role", "1")
                .header("UserName", "20131346055")
                .header("Role", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void singnup() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("userName", "20131346055")
                .param("password", "def5e35d3d2a9e985e5bbb6b02465fd5")
                .param("role", "1")
                .header("UserName", "20131346055")
                .header("Role", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void operation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/user/operation")
                .header("UserName", "20131346055")
                .header("Role", "1")
                .header("tk", "59c91a4a853b43b2ad4ce4945a944a88")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}