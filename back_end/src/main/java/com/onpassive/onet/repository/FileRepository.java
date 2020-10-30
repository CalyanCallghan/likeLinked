package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onpassive.onet.entity.Post;


@Repository
public interface FileRepository extends CrudRepository<Post,Integer>{
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update user u set u.file_name=?1 where u.emp_id = ?2",nativeQuery = true)
	int updateProfilePic(String fileName,long userId);
	
	@Query("SELECT p FROM Post p WHERE LOWER(p.type) = LOWER(:type) order by p.id desc")
    public List<Post> returnAllthePosts(String type);
}
