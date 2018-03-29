package com.chronoswood.doublechoose.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AccountVO {
    /**
     * 工号/学号/管理员
     */
    @NotEmpty
    @Length(max = 20, message = "账户必须在20位之内")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty
    @Length(max = 128, message = "密码长度必须在128位之内")
    private String password;

    /**
     * 角色权限   0为学生，1为教授，2为管理员
     */
    @NotNull
    private Integer role;

}
