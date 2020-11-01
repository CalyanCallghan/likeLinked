package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onpassive.onet.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
