package com.chronoswood.doublechoose.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorVO extends UserCommonVO {
    private String photoURL;

    private String introduction;

    private String awards;

    private String researchDirection;

    private String college;

    private String tel;

    private String email;

    private String title;
}
