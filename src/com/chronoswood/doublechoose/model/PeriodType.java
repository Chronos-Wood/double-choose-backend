package com.chronoswood.doublechoose.model;

public enum PeriodType {
    CHOOSE_PROJECT(1, "选课题"),
    CHOOSE_STUDENT(2, "选学生"),
    PUBLISH(3,"公布结果")
    ;

    private int code;
    private String description;

    PeriodType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
