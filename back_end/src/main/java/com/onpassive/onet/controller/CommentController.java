package com.onpassive.onet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.entity.CommentLike;
import com.onpassive.onet.entity.SubComment;
import com.onpassive.onet.exception.ResourceNotFoundException;
import com.onpassive.onet.model.CommentDetails;
import com.onpassive.onet.repository.CommentLikeRepository;
import com.onpassive.onet.repository.CommentRepository;
import com.onpassive.onet.repository.PostLikeRepository;
import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.repository.SubCommentRepository;
import com.onpassive.onet.service.CommentsAndLikesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostLikeRepository likeRepository;

	@Autowired
	private SubCommentRepository subCommentRepository;

	@Autowired
	private CommentLikeRepository commentlikeRepository;
	
	@Autowired
	private CommentsAndLikesService commentService;

	// to store post comments
	@PostMapping("/posts/{postId}/comments")
	public Comment createComment(@PathVariable(value = "postId") Integer postId, @Valid @RequestBody Comment comment) {
		return postRepository.findById(postId).map(post -> {
			comment.setPost(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	// to get comments of post through postId
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDetails>> getAllCommentsByPostId(@PathVariable(value = "postId") Integer postId) {
	List<CommentDetails> commentDtetails = new ArrayList<>();
		List<Object[]> allComments =commentRepository.getAllCommentsByPostId(postId);
		for(Object[] objArr : allComments)
	    {
			commentDtetails.add(new CommentDetails(((Integer)objArr[0]),((String)objArr[1]),((String)objArr[2])));
		}
		return new ResponseEntity<List<CommentDetails>>(commentDtetails, new HttpHeaders(), HttpStatus.OK);
	}

	// to update comment of post by commentId
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public Comment updateComment(@PathVariable(value = "postId") Integer postId,
			@PathVariable(value = "commentId") Integer commentId, @Valid @RequestBody Comment commentRequest) {
		if (!postRepository.existsById(postId)) {
			throw new ResourceNotFoundException("PostId " + postId + " not found");
		}

		return commentRepository.findById(commentId).map(comment -> {
			comment.setContent(commentRequest.getContent());
			comment.setEmpId(commentRequest.getEmpId());
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
	}

	// to delete comment by commentId
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Integer postId,
			@PathVariable(value = "commentId") Integer commentId) {
		return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Comment not found with id " + commentId + " and postId " + postId));
	}

	// to store post likes by individual employees
	@GetMapping("/posts/{employeeId}/{postId}/likes")
	public long updateOrcreateLikes(@PathVariable(value = "postId") int postId, @PathVariable(value = "employeeId") int employeeId) {
		long count =0;
		System.err.println("postId---->"+postId+"----employeeId--->"+employeeId);
		count = commentService.updateAndReturnLikesCount(employeeId, postId);
		return count;
	}

	// to get post like count
	@GetMapping("/posts/{postId}/postLikesCount")
	public long likesCount(@PathVariable(value = "postId") Integer postId) {
		long likesCount = likeRepository.countByIsliked(postId);
		System.out.println("checkCount:::" + likesCount);
		return likesCount;
	}

	// to store sub-comments of comment
	@PostMapping("/comment/{commentId}/subComments")
	public SubComment createSubComment(@PathVariable(value = "commentId") Integer commentId,
			@Valid @RequestBody SubComment subComment) {
		return commentRepository.findById(commentId).map(comment -> {
			subComment.setComment(comment);
			return subCommentRepository.save(subComment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + commentId + " not found"));
	}

	// to get comments of post through postId
	@GetMapping("/comment/{commentId}/subcomments")
	public Optional<SubComment> getAllSubCommentsByCommentId(@PathVariable(value = "commentId") Integer commentId) {
		return subCommentRepository.findById(commentId);
	}

	// to store comment likes
	@PostMapping("/Comment/{commentId}/likes")
	public CommentLike commentLikes(@PathVariable(value = "commentId") Integer commentId,
			@Valid @RequestBody CommentLike commentlikes) {
		return commentRepository.findById(commentId).map(commentIsLike -> {

			System.out.println(commentlikes.getEmpId());

			commentlikes.setLiked(true);
			commentlikes.setComment(commentIsLike);

			return commentlikeRepository.save(commentlikes);
		}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
	}

	// to get comment likes count
	@GetMapping("/Comment/{commentId}/commentLikesCount")
	public long commentLikesCount(@PathVariable(value = "commentId") Integer commentId) {

		return commentlikeRepository.getCommentLikesCountByCommentId(commentId);
	}

	// to get post comment count
	@GetMapping("/posts/{postId}/commentCount")
	public long postCommentCount(@PathVariable(value = "postId") Integer postId) {

		return commentlikeRepository.getCommentCountByPostId(postId);
	}

}
