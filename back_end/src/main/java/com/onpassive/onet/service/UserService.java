package com.onpassive.onet.service;

import java.util.List;

import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.UserDetails;

public interface  UserService {

	List<User> findAll();

	List<User> saveUsers(List<User> requestList);

	void deleteUser(Long empId);
	
	List<UserDetails> findAllUser();
	
	User updateUser(User user);
}
