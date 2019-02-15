package com.resource.demo.http.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class HelloResponse {

    private Long id;

    private String description;

    public HelloResponse() {
    }

    public HelloResponse(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
