package com.chronoswood.doublechoose.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Project {

    private Integer id;
    private String name;
    private String preview_image;
    private String description;
    private Integer director_id;
    private Integer period_id;
    private String begin;//是否有存时间的数据类型
    private String end;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
