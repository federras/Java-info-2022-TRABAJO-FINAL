package com.informatorio.trabajofinal.config;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {
    private HttpStatus status;
    private int cantErrors;
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message, int cantErrors, List<ApiSubError> subErrors) {
        this.status = status;
        this.cantErrors = cantErrors;
        this.subErrors = subErrors;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }

    public ApiError() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCantErrors() {
        return cantErrors;
    }

    public void setCantErrors(int cantErrors) {
        this.cantErrors = cantErrors;
    }

}
