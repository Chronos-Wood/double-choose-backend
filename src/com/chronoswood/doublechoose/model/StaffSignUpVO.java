package com.chronoswood.doublechoose.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class StaffSignUpVO {
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 10, message = "姓名长度过长")
    private String staffName;

    @NotNull(message = "性别不能为空")
    @Min(0)
    @Max(1)
    private Integer staffSex;

    @NotEmpty(message = "学院不能为空")
    private String college;

    @NotEmpty(message = "职称不能为空")
    private String title;
}
