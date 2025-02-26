package com.group2.hospitally.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppResponse<T> {
    private String responseCode;

    private String responseMessage;

    private T data;
}
