package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onpassive.onet.entity.BatchProcessEntity;


public interface BatchProcessRepository extends JpaRepository<BatchProcessEntity, Long>{
	public List<BatchProcessEntity> findByStepName(String stepName);
}
