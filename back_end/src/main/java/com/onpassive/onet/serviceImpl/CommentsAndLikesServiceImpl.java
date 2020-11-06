package com.onpassive.onet.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpassive.onet.entity.Post;
import com.onpassive.onet.entity.PostLike;
import com.onpassive.onet.repository.PostLikeRepository;
import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.service.CommentsAndLikesService;

@Service
public class CommentsAndLikesServiceImpl implements CommentsAndLikesService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;

	@Override
	public long updateAndReturnLikesCount(int employeeId, int postId) {
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
}
