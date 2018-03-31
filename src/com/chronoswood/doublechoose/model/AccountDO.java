package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDO {
    private Integer id;

    private String userName;

    private Integer role;

    private String password;

    private String salt;

    private Integer authorized;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
