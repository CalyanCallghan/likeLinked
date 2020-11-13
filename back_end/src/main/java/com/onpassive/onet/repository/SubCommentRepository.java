package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onpassive.onet.entity.SubComment;

public interface SubCommentRepository extends JpaRepository<SubComment, Integer> {
	
	//public List<SubComment> findSubCommentsByPostId(int postId);
		


}
