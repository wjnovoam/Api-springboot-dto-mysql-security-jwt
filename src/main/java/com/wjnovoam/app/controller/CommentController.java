package com.wjnovoam.app.controller;

import com.wjnovoam.app.dto.CommentDTO;
import com.wjnovoam.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/publications/{publicationId}/comments")
    public ResponseEntity<List<CommentDTO>> listCommentsForPublication(@PathVariable(value = "publicationId") Long publicationId){
        return new ResponseEntity<>(commentService.getAllCommentsForPublication(publicationId), HttpStatus.OK);
    }

    @GetMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentForId(@PathVariable(value = "publicationId") Long publicationId,
                                                      @PathVariable(value = "commentId") Long commentId){
        return new ResponseEntity<>(commentService.getCommentForId(publicationId, commentId), HttpStatus.OK);
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "publicationId") Long publicationId,@Valid @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(publicationId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "publicationId") Long publicationId,
                                                      @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDTO requesComment){
        return new ResponseEntity<>(commentService.updateComment(publicationId, commentId, requesComment), HttpStatus.OK);
    }

    @DeleteMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentForId(@PathVariable(value = "publicationId") Long publicationId,
                                                      @PathVariable(value = "commentId") Long commentId){
        commentService.deleteComment(publicationId, commentId);
        return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
    }
}
