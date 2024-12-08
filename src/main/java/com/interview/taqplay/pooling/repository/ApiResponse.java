package com.interview.taqplay.pooling.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApiResponse {
    private int httpStatus;
    private Object data;
}
