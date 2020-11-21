package com.onpassive.onet.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.entity.SubComment;
import com.onpassive.onet.exception.ResourceNotFoundException;
import com.onpassive.onet.model.CommentDetails;
import com.onpassive.onet.repository.CommentLikeRepository;
import com.onpassive.onet.repository.CommentRepository;
import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.repository.SubCommentRepository;
import com.onpassive.onet.service.CommentsAndLikesService;
import com.onpassive.onet.util.TimeUtills;

import io.micrometer.core.instrument.util.TimeUtils;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private SubCommentRepository subCommentRepository;
	
	@Autowired
	private CommentsAndLikesService commentService;
	
	@Autowired
	private CommentLikeRepository commentlikeRepository;

	// to store post comments
	@PostMapping("/postComment/{postId}")
	public Comment createComment(@PathVariable(value = "postId") Integer postId, @Valid @RequestBody Comment comment) {
		return postRepository.findById(postId).map(post -> {
			comment.setPost(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	
	
	@GetMapping("/allCommentsOfPost/{postId}")
	public ResponseEntity<List<CommentDetails>> getAllCommentsByPostId(@PathVariable(value = "postId") Integer postId) {
		List<CommentDetails> commentDtetails = new ArrayList<>();
		List<Object[]> allComments = commentRepository.getAllCommentsByPostId(postId);
		for (Object[] objArr : allComments) {
			LocalDateTime localDateTime = ((Timestamp) objArr[5]).toLocalDateTime();
			String when = TimeUtills.convertToSpecificFormatLocalDateTime(localDateTime);
			//System.err.println("--66->"+objArr[6]);
			//System.err.println("--77->"+objArr[7]);
			commentDtetails.add(new CommentDetails(((Integer) objArr[0]), ((String) objArr[1]), ((String) objArr[2]),
					((String) objArr[3]), ((String) objArr[4]), when,(((BigInteger)objArr[6]).longValue()),(((BigInteger)objArr[7]).longValue())));
		}
		return new ResponseEntity<List<CommentDetails>>(commentDtetails, new HttpHeaders(), HttpStatus.OK);
	}
	
	// to get post comment count
	@GetMapping("/commentsCountOfPost/{postId}")
	public long postCommentCount(@PathVariable(value = "postId") Integer postId) {
		return commentlikeRepository.getCommentCountByPostId(postId);
	}
	

	// to store likes of post and return no of likes to post by postId
	@GetMapping("/postLikes/{employeeId}/{postId}")
	public long updateOrcreateLikes(@PathVariable(value = "postId") int postId, @PathVariable(value = "employeeId") int employeeId) {
		long count =0;
		System.err.println("postId---->"+postId+"----employeeId--->"+employeeId);
		count = commentService.updateAndReturnLikesCountOfPost(employeeId, postId);
		return count;
	}


	// to store sub-comments of comment
	@PostMapping("/postSubcomment/{commentId}")
	public SubComment createSubComment(@PathVariable(value = "commentId") Integer commentId,
			@Valid @RequestBody SubComment subComment) {
		System.err.println("-----------commentId----------"+commentId);
		return commentRepository.findById(commentId).map(comment -> {
			subComment.setComment(comment);
			return subCommentRepository.save(subComment);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + commentId + " not found"));
	}
	
	@GetMapping("/allSubCommentsOfComment/{commentId}")
	public ResponseEntity<List<CommentDetails>> getAllSubCommentsByCommentId(
			@PathVariable(value = "commentId") Integer commentId) {
		
		List<CommentDetails> commentDetails = new ArrayList<>();
		List<Object[]> allSubComments = subCommentRepository.getAllSubCommentsByCommentId(commentId);
		for (Object[] objArr : allSubComments) {
			LocalDateTime localDateTime = ((Timestamp) objArr[5]).toLocalDateTime();
			String when = TimeUtills.convertToSpecificFormatLocalDateTime(localDateTime);
			commentDetails.add(new CommentDetails(((Integer) objArr[0]), ((String) objArr[1]), ((String) objArr[2]),
					((String) objArr[3]), ((String) objArr[4]),when,(((BigInteger)objArr[6]).longValue()),1));
		}
		return new ResponseEntity<List<CommentDetails>>(commentDetails, new HttpHeaders(), HttpStatus.OK);
	}

	
	// to store likes of comments and return no of likes of comment by commentId
	@GetMapping("/commentLikes/{employeeId}/{commentId}")
	public long updateOrcreateLikesToComment(@PathVariable(value = "commentId") int commentId, @PathVariable(value = "employeeId") int employeeId) {
		long count =0;
		System.err.println("commentId---->"+commentId+"----employeeId--->"+employeeId);
		count = commentService.updateAndReturnLikesCountOfComment(employeeId, commentId);
		return count;
	}
	
	
	// to get post comment count
	@GetMapping("/subCommentsCountOfComment/{commentId}")
	public long subCommentsCountOfComment(@PathVariable(value = "commentId") Integer commentId) {
		return subCommentRepository.getSubCommentCountByCommentId(commentId);
	}
	
	// to store likes of comments and return no of likes of comment by commentId
	@GetMapping("/subcommentLikes/{employeeId}/{subCommentId}")
	public long updateOrcreateLikesToSubComment(@PathVariable(value = "subCommentId") int subCommentId,
			@PathVariable(value = "employeeId") int employeeId) {
		long count = 0;
		System.err.println("commentId---->" + subCommentId + "----employeeId--->" + employeeId);
		count = commentService.updateAndReturnLikesCountOfSubComment(employeeId, subCommentId);
		return count;
	}
	
	
	
	/*
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
	*/	

}
