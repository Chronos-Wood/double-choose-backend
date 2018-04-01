package com.chronoswood.doublechoose.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentVO extends UserCommonVO {
    private String photoURL;
    private String introduction;
    private String interest;
    private String awards;
    private String researchDirection;
}
