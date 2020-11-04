package com.onpassive.onet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.Comment;
import com.onpassive.onet.model.CommentDetails;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

//	@Query("select new com.onpassive.onet.model.CommentDetails(c.id,c.empId,c.content) from Post p, Comment c where p.id=:postId ")
	//select new com.onpassive.onet.model.CommentDetails(c.id,c.empId,c.content) from Post p, Comment c where p.id= c.post_id:=postId
//	select * from comments c, post p where p.id = c.post_id and c.post_id=1 ;
	
	@Query(value = "select comments.id,comments.emp_id,comments.content from comments , post  where post.id = comments.post_id and comments.post_id=:postId",nativeQuery = true)
//	select c.id,c.emp_id,c.content from comments c, post p where p.id = c.post_id and c.post_id=1 ;
	public List<Object[]> getAllCommentsByPostId(int postId);
	
    Optional<Comment> findByIdAndPostId(Integer id, Integer postId);
    
    
    

}
