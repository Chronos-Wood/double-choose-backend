package com.chronoswood.doublechoose.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class AcceptingWillsRequest {
    @SuppressWarnings("unchecked")
    List<String> willIds = Collections.EMPTY_LIST;
}
