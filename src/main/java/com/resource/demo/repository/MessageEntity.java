package com.resource.demo.repository;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public MessageEntity() {
    }

    public MessageEntity(Long id, String message) {
        if (id != null && message != null) {
            this.id = id;
            this.message = message;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
