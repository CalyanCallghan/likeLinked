package com.onpassive.onet.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.onpassive.onet.entity.User;

@Component
public class UsersDetailsProcessor  implements ItemProcessor<User, User> {

	@Override
	public User process(User user) throws Exception {
		return user;
	}
	

}
