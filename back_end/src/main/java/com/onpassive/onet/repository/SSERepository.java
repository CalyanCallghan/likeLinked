package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.SSEModel;



@Repository
public interface SSERepository extends JpaRepository<SSEModel, Integer> {

}

