package com.onpassive.onet.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.repository.UserRepository;

@Component
public class UserDetailsWritter  implements ItemWriter<User>{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void write(List<? extends User> items) throws Exception {
		//userRepository.u
		userRepository.saveAll(items);
		
	}
}
