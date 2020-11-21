package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onpassive.onet.entity.CommentLike;

@Repository
public interface CommentLikeRepository extends CrudRepository<CommentLike, Integer> {
	
	@Query( value = "SELECT count(*) FROM comment_like where comment_id=?1" , nativeQuery = true)
	public long getCommentLikesCountByCommentId(Integer commentId);
	
	@Query( value = "SELECT count(*) FROM comments where post_id=?1" , nativeQuery = true)
	public long getCommentCountByPostId(Integer postId);
	
	
	CommentLike findByempIdAndCommentId(String empId, Integer commentId);
	
	
	@Query( value = "SELECT count(*) FROM comment_like where is_liked='1' and comment_id=:commentId" , nativeQuery = true)
	public long likescountByCommentId(int commentId);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update comment_like set is_liked=:status where comment_id=:commentId and emp_id=:empId", nativeQuery = true)
	int updateIsLikedByEmployeeID(Integer empId, Integer commentId,boolean status);
	

}
