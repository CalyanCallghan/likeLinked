package com.onpassive.onet.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.exception.ResourceNotFoundException;
import com.onpassive.onet.repository.CommentRepository;
import com.onpassive.onet.repository.PostRepository;
@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


	@GetMapping("/posts/{postId}/comments")
    public Optional<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Integer postId) {
        return commentRepository.findById(postId);
    }
	
	@PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Integer postId,
                                 @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
	
	/*@PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Integer postId,
                                 @PathVariable (value = "commentId") Integer commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(commentRequest.getContent());
            comment.setEmpId(commentRequest.getEmpId());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Integer postId,
                              @PathVariable (value = "commentId") Integer commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }*/
	
	
}
