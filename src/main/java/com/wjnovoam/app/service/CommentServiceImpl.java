package com.wjnovoam.app.service;

import com.wjnovoam.app.dto.CommentDTO;
import com.wjnovoam.app.entitys.Comment;
import com.wjnovoam.app.entitys.Publication;
import com.wjnovoam.app.exceptions.BlogAppException;
import com.wjnovoam.app.exceptions.ResourceNotFountException;
import com.wjnovoam.app.repository.CommentRepository;
import com.wjnovoam.app.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public CommentDTO createComment(Long publicationId, CommentDTO commentDTO) {

        Comment comment = mapearEntidad(commentDTO);

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", publicationId));

        comment.setPublication(publication);

        Comment newComment = commentRepository.save(comment);
        return mapearDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllCommentsForPublication(Long publicationId) {

        List<Comment> comments = commentRepository.findByPublicationId(publicationId);
        return comments.stream().map(comment -> mapearDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentForId(Long publicationId, Long commentId) {

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", publicationId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFountException("Comment", "id", commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        return mapearDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long publicationId,Long commentId ,CommentDTO requestComment) {

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", publicationId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFountException("Comment", "id", commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        comment.setName(requestComment.getName());
        comment.setEmail(requestComment.getEmail());
        comment.setBody(requestComment.getBody());

        Comment commentUpdate = commentRepository.save(comment);

        return mapearDTO(commentUpdate);
    }

    @Override
    public void deleteComment(Long publicationId, Long commentId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFountException("Publication", "id", publicationId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFountException("Comment", "id", commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        commentRepository.delete(comment);
    }

    private CommentDTO mapearDTO(Comment comment){
        CommentDTO commentDTO  = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    private Comment mapearEntidad(CommentDTO commentDTO){
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }
}
