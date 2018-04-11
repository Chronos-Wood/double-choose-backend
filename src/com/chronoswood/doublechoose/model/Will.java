package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Will {
    private String id;
    private String projectName;
    private String projectId;
    private String previewImageURL;
    private String projectDescription;
    private String directorId;
    private String directorName;
    private String studentId;
    private String studentName;
    private String periodId;
    private LocalDateTime projectBeginTime;
    private LocalDateTime projectEndTime;
    private String createTime;
    private String updateTime;
    private Boolean accepted;
    private int precedence;
}
