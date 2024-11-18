package com.citronix.dto.res;

import lombok.Data;

@Data
public class ValidationResponse {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    public ValidationResponse(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ValidationResponse(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.message = message;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }
}
