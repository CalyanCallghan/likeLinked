package com.onpassive.onet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.CommentText;

@Repository
public interface CommentRepository extends CrudRepository<CommentText, Integer> {

}
