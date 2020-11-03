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
	@Query(value ="update user u set u.file_name=?1 where u.emp_id = ?2",nativeQuery = true)
	int updateProfilePic(String fileName,long userId);
	
	@Query("select new com.onpassive.onet.model.PostDetails(p.postId,p.fileName,p.description,p.format,p.type,u.firstName,u.lastName,u.fileName) from Post p, User u where p.createdBy= u.empId and p.type=:type order by p.postId desc")
	public List<PostDetails> allthePostsData(String type);
	
	@Query("select new com.onpassive.onet.model.PostDetails(p.postId,p.fileName,p.description,p.format,p.type,u.firstName,u.lastName,u.fileName) from Post p, User u where p.createdBy= u.empId and p.postId=:postId ")
	public PostDetails specificPostData(int postId);


}
