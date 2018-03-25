package com.chronoswood.doublechoose.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    SUCCESS(10000, "请求成功"),

    SERVER_ERROR(500100, "服务器异常"),

    BIND_ERROR(500300, "参数绑定异常: %s");

    private Integer code;

    private String message;

    public Message bindArgs(Object... args) {
        this.message = String.format(this.message, args);
        return this;
    }
}
