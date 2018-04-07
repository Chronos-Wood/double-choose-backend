package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Period {
    private long id;
    private String description;
    private LocalDateTime begin;
    private LocalDateTime end;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
