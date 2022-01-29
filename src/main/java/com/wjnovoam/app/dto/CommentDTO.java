package com.wjnovoam.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDTO {

    private Long id;

    @NotEmpty(message = "The name must not be empty or null")
    private String name;

    @NotEmpty(message = "The email must not be empty or null")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "the comment body must have at least 10 characters")
    private String body;

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
