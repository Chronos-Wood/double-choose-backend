package com.chronoswood.doublechoose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {
    private String token;

    private Integer role;

    private String userName;

}
