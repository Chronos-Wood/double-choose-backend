package com.chronoswood.doublechoose.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class StudentSignUpVO {

    @NotEmpty(message = "姓名不能为空")
    @Length(max = 10, message = "姓名长度过长")
    private String studentName;

    @NotNull(message = "性别不能为空")
    @Min(0)
    @Max(1)
    private Integer studentSex;

}
