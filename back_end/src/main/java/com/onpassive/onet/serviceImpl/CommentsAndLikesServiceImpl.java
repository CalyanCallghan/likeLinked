package com.onpassive.onet.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.entity.CommentLike;
import com.onpassive.onet.entity.Post;
import com.onpassive.onet.entity.PostLike;
import com.onpassive.onet.entity.SubComment;
import com.onpassive.onet.entity.SubCommentLike;
import com.onpassive.onet.repository.CommentLikeRepository;
import com.onpassive.onet.repository.CommentRepository;
import com.onpassive.onet.repository.PostLikeRepository;
import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.repository.SubCommentLikeRepository;
import com.onpassive.onet.repository.SubCommentRepository;
import com.onpassive.onet.service.CommentsAndLikesService;

@Service
public class CommentsAndLikesServiceImpl implements CommentsAndLikesService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private SubCommentRepository subCommentRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Autowired
	private CommentLikeRepository commentLikeRepository;

	@Autowired
	private SubCommentLikeRepository subCommentLikeRepository;

	@Override
	public long updateAndReturnLikesCountOfPost(int employeeId, int postId) {
		long likesCount = 0;
		PostLike postLike = postLikeRepository.findByempIdAndPostId(employeeId+"",postId);
		System.err.println("postLike---->"+postLike+"----postId--->"+postId);
		if(postLike != null) {
			System.err.println("isLiked---->"+postLike.isLiked()+"----!=--"+!postLike.isLiked());
			postLikeRepository.updateIsLikedByEmployeeID(employeeId, postId,!postLike.isLiked());
		}else {
			postLike =  new PostLike();
			Post post = postRepository.findById(postId);
			postLike.setEmpId(employeeId+"");
			postLike.setPost(post);
			postLike.setLiked(true);
			postLikeRepository.save(postLike);
		}
		likesCount = postLikeRepository.countByIsliked(postId);
		return likesCount;
	}
	
	@Override
	public long updateAndReturnLikesCountOfComment(int empId, int commentId) {
		long likesCount = 0;
		CommentLike commentLike = commentLikeRepository.findByempIdAndCommentId(empId+"", commentId);
		System.err.println("postLike---->"+commentLike+"----postId--->"+commentId);
		if(commentLike != null) {
			System.err.println("isLiked---->"+commentLike.isLiked()+"----!=--"+!commentLike.isLiked());
			commentLikeRepository.updateIsLikedByEmployeeID(empId, commentId,!commentLike.isLiked());
		}else {
			commentLike =  new CommentLike();
			Comment comment = commentRepository.findById(commentId);
			commentLike.setEmpId(empId+"");
			commentLike.setComment(comment);
			commentLike.setLiked(true);
			commentLikeRepository.save(commentLike);
		}
		likesCount = commentLikeRepository.likescountByCommentId(commentId);
		return likesCount;
	}
	
	@Override
	public long updateAndReturnLikesCountOfSubComment(int empId, int subCommentId) {
		long likesCount = 0;
		SubCommentLike subCommentLike = subCommentLikeRepository.findByempIdAndSubCommentId(empId+"", subCommentId);
		System.err.println("postLike---->"+subCommentLike+"----postId--->"+subCommentId);
		if(subCommentLike != null) {
			System.err.println("isLiked---->"+subCommentLike.isLiked()+"----!=--"+!subCommentLike.isLiked());
			subCommentLikeRepository.updateIsLikedByEmployeeID(empId, subCommentId,!subCommentLike.isLiked());
		}else {
			subCommentLike =  new SubCommentLike();
			SubComment subComment = subCommentRepository.findById(subCommentId);
			subCommentLike.setEmpId(empId+"");
			subCommentLike.setSubComment(subComment);
			subCommentLike.setLiked(true);
			subCommentLikeRepository.save(subCommentLike);
		}
		likesCount = subCommentLikeRepository.likesCountOfSubCommentId(subCommentId);
		return likesCount;
	}
}
