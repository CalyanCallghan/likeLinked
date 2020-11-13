package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onpassive.onet.entity.Post;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.UserDetails;

public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findAll();
		
	@Query("SELECT new com.onpassive.onet.model.UserDetails(u.email, u.fileName, u.firstName, u.lastName, u.groupId) FROM User u where u.email=?1")
	UserDetails findUserByEmailId(String firstName);

}
