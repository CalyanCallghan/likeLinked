
package com.onpassive.onet.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpassive.onet.entity.User;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.ExcelService;
import com.onpassive.onet.util.ExcelHelper;

@Service
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ByteArrayInputStream load() {
		List<User> users = userRepository.findAll();
		return ExcelHelper.userToexcel(users);
	}

}
