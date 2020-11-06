package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onpassive.onet.entity.PostLike;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike, Integer> {
	@Query( value = "SELECT count(*) FROM opnetdb.post_like where is_liked='1' and post_id=:postId " , nativeQuery = true)
	public long countByIsliked(int postId);
	
	PostLike findByempIdAndPostId(String empId, Integer postId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update post_like set is_liked=:status where post_id=:postId and emp_id=:empId", nativeQuery = true)
	int updateIsLikedByEmployeeID(Integer empId, Integer postId,boolean status);
}
