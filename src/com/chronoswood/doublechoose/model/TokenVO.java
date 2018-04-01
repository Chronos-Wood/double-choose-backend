package com.chronoswood.doublechoose.model;

import lombok.Data;

@Data
public class TokenVO {
    private String token;

    private UserCommonVO user;

    public TokenVO() {
    }
    public TokenVO(String token, UserCommonVO userCommonVO) {
        this.token = token;
        this.user = userCommonVO;
    }
}
