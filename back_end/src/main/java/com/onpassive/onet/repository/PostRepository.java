package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onpassive.onet.entity.Post;
import com.onpassive.onet.model.PostDetails;


@Repository
public interface PostRepository extends CrudRepository<Post,Integer>{
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update user u set u.profile_pic_name=?1 where u.emp_id = ?2",nativeQuery = true)
	int updateProfilePic(String fileName,long userId);
	
	@Query("select new com.onpassive.onet.model.PostDetails(p.id,p.fileName,p.description,p.format,p.type,u.firstName,u.lastName,u.fileName) from Post p, User u where p.createdBy= u.empId and p.type=:type order by p.id desc")
	public List<PostDetails> allthePostsData(String type);
	
	@Query("select new com.onpassive.onet.model.PostDetails(p.id,p.fileName,p.description,p.format,p.type,u.firstName,u.lastName,u.fileName) from Post p, User u where p.createdBy= u.empId and p.id=:postId ")
	public PostDetails specificPostData(int postId);
	
	@Query(value = "select p.id,p.file_name,p.description,p.format,p.type,u.first_name,u.last_name,u.profile_pic_name,"
			+ "(select count(*) from post_like where post_like.is_liked=1 and post_like.post_id = p.id ) as likeCount,"
			+" (select count(*) from comments where comments.post_id = p.id ) as commentCount"
			+ " from post p, user u where p.created_by= u.emp_Id  and p.type=:type order by p.id desc", nativeQuery = true)
	public List<Object[]> allthePostsDataWithCountAndLike(String type);
	
	
	public Post findById(int postId);

}
