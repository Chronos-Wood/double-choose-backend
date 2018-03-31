package com.chronoswood.doublechoose.model;

import lombok.Data;

import javax.validation.Valid;

@Data
public class SignUpVO {

    @Valid
    private StudentSignUpVO studentSignUpVO;

    @Valid
    private StaffSignUpVO staffSignUpVO;

    @Valid
    private AccountVO accountVO;
}
