package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.entity.SubComment;

public interface SubCommentRepository extends JpaRepository<SubComment, Integer> {
	
	@Query(value = "select sub_comment.id,user.first_name,user.last_name,user.profile_pic_name,sub_comment.content,sub_comment.created_at, "
			+ "(select count(*) from sub_comment_like where sub_comment_like.sub_comment_id = sub_comment.id) as likesCount from sub_comment, comments, user"
			+ " where sub_comment.emp_id= user.emp_Id and comments.id = sub_comment.comment_id and sub_comment.comment_id=:commentId",nativeQuery = true)
	public List<Object[]> getAllSubCommentsByCommentId(Integer commentId);
	
	@Query( value = "SELECT count(*) FROM sub_comment where comment_id=?1" , nativeQuery = true)
	public long getSubCommentCountByCommentId(Integer commentId);
	
	 public SubComment findById(int postId);

}
