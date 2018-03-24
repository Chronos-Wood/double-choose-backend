package com.chronoswood.doublechoose.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @RequestMapping("/")
    public String homepage(@RequestBody String content){
        LOGGER.info("handle request content：{}", content);
        return "";
    }
}
