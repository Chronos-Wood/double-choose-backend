package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ChoosingProjectRequest {
    @SuppressWarnings("unchecked")
    List<String> projectIds = Collections.EMPTY_LIST;

    AccountVO accountVO;
}
