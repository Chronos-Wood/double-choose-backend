package com.chronoswood.doublechoose.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    SUCCESS(10000, "请求成功"),

    SERVER_ERROR(500100, "服务器异常"),
    SESSION_EXPIRED(500101, "session过期"),
    USER_NAME_REQUIRED(500200, "账号不能为空"),
    PASSWORD_REQUIRED(500201, "密码不能为空"),
    ROLE_REQUIRED(500202, "角色未知"),
    USER_NOT_EXIST(500203, "账户不存在"),
    WRONG_PASSWORD(500204, "密码错误"),
    USER_EXIST(500205, "账户已注册"),
    UNAUTHORIZED_USER(500206, "账户未审核，请稍后再试"),
    NO_PERMISSION(500207, "无访问权限"),

    BIND_ERROR(500300, "参数绑定异常: %s"),
    BIZ_ERROR(500400, "业务处理异常: %s"),
    INSERT_ERROR(500500, "数据库异常");

    private Integer code;

    private String message;

    public Message bindArgs(Object... args) {
        this.message = String.format(this.message, args);
        return this;
    }
}
