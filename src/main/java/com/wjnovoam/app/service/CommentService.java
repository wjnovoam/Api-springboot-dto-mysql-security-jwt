package com.wjnovoam.app.service;

import com.wjnovoam.app.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long publicationId, CommentDTO commentDTO);
    List<CommentDTO> getAllCommentsForPublication(Long publicationId);
    CommentDTO getCommentForId(Long publicationId, Long commentId);
    CommentDTO updateComment(Long publicationId,Long commentId ,CommentDTO requestComment);
    void deleteComment(Long publicationId,Long commentId);
}
