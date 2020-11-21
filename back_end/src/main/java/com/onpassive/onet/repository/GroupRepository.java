package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.Group;
import com.onpassive.onet.model.GroupModel;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

	@Query("SELECT new com.onpassive.onet.model.GroupModel(u.empId,u.email,u.firstName) FROM User u , Group g WHERE u.groupId = g.id AND g.id = ?1")
	public List<GroupModel> findByGroupId(Integer groupId);

}
