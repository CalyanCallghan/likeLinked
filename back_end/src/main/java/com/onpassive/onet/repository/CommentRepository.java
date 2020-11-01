package com.onpassive.onet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

	Optional<Comment> findById(Integer postId);
	//List<Comment> findByPostId(Long postId);
    //Optional<Comment> findByIdAndPostId(Integer id, Integer postId);

}
