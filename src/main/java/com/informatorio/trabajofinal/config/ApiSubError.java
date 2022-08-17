package com.informatorio.trabajofinal.config;

public class ApiSubError {
    private String field;
    private String message;

    public ApiSubError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ApiSubError() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiSubError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
