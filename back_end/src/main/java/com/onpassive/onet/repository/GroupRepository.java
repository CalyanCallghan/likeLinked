package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.GroupModel;


@Repository
public interface GroupRepository extends JpaRepository<GroupModel, String> {

}
