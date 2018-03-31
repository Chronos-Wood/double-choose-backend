package com.chronoswood.doublechoose.model;

import lombok.Data;

@Data
public class TokenVO {
    private String token;

    public TokenVO() {
    }
    public TokenVO(String token) {
        this.token = token;
    }
}
