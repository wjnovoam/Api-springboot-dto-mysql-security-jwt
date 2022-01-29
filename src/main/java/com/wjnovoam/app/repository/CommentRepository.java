package com.wjnovoam.app.repository;

import com.wjnovoam.app.entitys.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPublicationId(Long publicationId);
}
