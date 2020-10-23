package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.TLModel;

@Repository
public interface TLRepository extends JpaRepository<TLModel, Integer> {



}
