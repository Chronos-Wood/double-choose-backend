package com.chronoswood.doublechoose.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Projects {

    private Director director;
    private Integer id;
    private String name;
    private String previewIamge;
    private String description;
    private Integer directorId;
    private Integer periodId;
    private String begin;//是否有存时间的数据类型
    private String end;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
