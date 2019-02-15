package com.resource.demo.http.data;

public class HelloRequest {

    private String message;

    public HelloRequest() {
    }

    public HelloRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
