package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
public class Student {
    private long id = -1;
    private String userName = "";
    private String name = "";
    private Integer gender = 0;
    private String photoURL = "";
    private String introduction = "";
    private String interest = "";
    private String awards = "";
    private String researchDirection = "";
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime udpateTime = LocalDateTime.now();
}
