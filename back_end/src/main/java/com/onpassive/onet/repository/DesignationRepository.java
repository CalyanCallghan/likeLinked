package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.Designation;


@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {

}
