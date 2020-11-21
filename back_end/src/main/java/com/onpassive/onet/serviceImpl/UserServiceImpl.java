package com.onpassive.onet.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.UserDetails;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long empId) {
		userRepository.deleteUser(empId);
	}

	@Override
	public List<User> saveUsers(List<User> requestList) {
		List<User> response = (List<User>) userRepository.saveAll(requestList);
		return response;

	}

	public List<UserDetails> findAllUser() {
		List<UserDetails> userDetails = new ArrayList<>();
		List<Object[]> allUsers = userRepository.findAllUser();
		for (Object[] objArr : allUsers) {
			userDetails.add(new UserDetails(((String) objArr[0]), ((String) objArr[1]), ((String) objArr[2]),
					(((String) objArr[3])), (((BigInteger) objArr[4]).longValue()), ((String) objArr[5])));
		}
		return userDetails;
	}

	@Override
	public User updateUser(User user) {
		long empId = user.getEmpId();
		User updatedUser = userRepository.findByEmpId(empId);
		if (user.getFirstName() != null)
			updatedUser.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			updatedUser.setLastName(user.getLastName());
		if (user.getPhoneNo() != null)
			updatedUser.setPhoneNo(user.getPhoneNo());
		return userRepository.save(updatedUser);
	}
}