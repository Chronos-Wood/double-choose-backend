package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    private static final int INITIAL_MAP_SIZE = 2;
    @RequestMapping("/")
    public String homepage(@RequestBody String content){
        LOGGER.info("handle request content：{}", content);
        return "";
    }

    @GetMapping("/")
    public Result<Map<String, Object>> testWrap() {
        Map<String, Object> params = new HashMap<>(INITIAL_MAP_SIZE);
        params.put("key1", "value1");
        params.put("key2", "value2");
        return Result.success(params);
    }
}
