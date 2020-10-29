package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onpassive.onet.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> findAll();
}
