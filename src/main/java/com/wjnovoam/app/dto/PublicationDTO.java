package com.wjnovoam.app.dto;

import com.wjnovoam.app.entitys.Comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class PublicationDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "The title of the publication should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "The description of the publication should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<Comment> comments;

    public PublicationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
