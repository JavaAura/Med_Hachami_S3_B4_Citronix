package com.citronix.dto.res;

import lombok.Builder;
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

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
