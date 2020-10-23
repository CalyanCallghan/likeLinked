package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.SEModel;

@Repository
public interface SERepository extends JpaRepository<SEModel, Integer> {

}
