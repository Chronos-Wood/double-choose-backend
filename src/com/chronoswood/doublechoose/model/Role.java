package com.chronoswood.doublechoose.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 账户类型
 */
public enum Role {
    UNKNOWN(-1, "未知的账户类型"),
    STUDENT(0,"学生"),
    STAFF(1, "教师"),
    ADMIN(2, "管理员");
    private int code;
    private String description;
    private static Map<Integer, Role> index = new HashMap<Integer, Role>(){{
       for (Role role : Role.values()){
           put(role.code, role);
       }
    }};

    Role(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Role getRole(int code){
        if(!index.containsKey(code)){
            return UNKNOWN;
        }
        return index.get(code);
    }
}
