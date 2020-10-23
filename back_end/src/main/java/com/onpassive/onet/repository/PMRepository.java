package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.PMModel;



@Repository
public interface PMRepository extends JpaRepository<PMModel, Integer> {



}