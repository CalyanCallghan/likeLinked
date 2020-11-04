package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.PostLike;

@Repository
public interface LikeRepository extends CrudRepository<PostLike, Integer> {
	@Query( value = "SELECT count(*) FROM opnetdb.post_like where is_liked='1' and post_id='1' " , nativeQuery = true)
	public long countByIsliked();

}
