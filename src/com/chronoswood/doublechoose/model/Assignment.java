package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Assignment {
    private Long id;
    private String projectName;
    private Long studentId;
    private String studentName;
    private Long directorId;
    private String directorName;
    private Long periodId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
