package com.onpassive.onet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.entity.Post;
import com.onpassive.onet.model.CommentDetails;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {


	@Query(value = "select comments.id,user.first_name,user.last_name,user.profile_pic_name,comments.content,comments.created_at,"
			+ "(select count(*) from comment_like where comment_like.is_liked=1 and comment_like.comment_id = comments.id ) as likeCount,"
			+ "(select count(*) from sub_comment where sub_comment.comment_id =comments.id ) as commentCount from comments, post, user"
			+ " where comments.emp_id= user.emp_Id and post.id = comments.post_id and comments.post_id=:postId",nativeQuery = true)
	public List<Object[]> getAllCommentsByPostId(int postId);
	
    Optional<Comment> findByIdAndPostId(Integer id, Integer postId);
    
    
    public Comment findById(int postId);
    
    
    

}
