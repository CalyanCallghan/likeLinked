package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.CommentLike;

@Repository
public interface CommentLikeRepository extends CrudRepository<CommentLike, Integer> {
	
	@Query( value = "SELECT count(*) FROM opnetdb.comment_like where comment_id=?1" , nativeQuery = true)
	public long getCommentLikesCountByCommentId(Integer commentId);
	
	@Query( value = "SELECT count(*) FROM opnetdb.comments where post_id=?1" , nativeQuery = true)
	public long getCommentCountByPostId(Integer postId);
	
	
	

}
