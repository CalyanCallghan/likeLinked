package com.onpassive.onet.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.Post;


@Repository
public interface FileRepository extends CrudRepository<Post,Integer>{

}
