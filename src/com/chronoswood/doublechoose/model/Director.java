package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Director {

    private Integer id;

    private String userName;

    private Integer gender;

    private String name;

    private String photoURL;

    private String introduction;

    private String awards;

    private String researchDirection;

    private String college;

    private String tel;

    private String email;

    private String title;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
