package com.petrpancocha.wholesale.exception.handler;

public class ErrorResponseDto {

    private int statusCode;
    private String message;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int statusCode, String message) {

        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}