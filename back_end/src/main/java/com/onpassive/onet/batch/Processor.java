package com.onpassive.onet.batch;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.onpassive.onet.entity.User;



@Component
public class Processor implements ItemProcessor<User, User> {

	private Set<User> seenAttendance = new HashSet<User>();

	@Override
	public User process(User item) throws Exception {
		System.out.println("========================="+seenAttendance.size());
		return item;
	}
	
}
