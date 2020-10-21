package com.onpassive.onet.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onpassive.onet.model.ChatModel;

public interface ChatRepository extends JpaRepository<ChatModel, Long> {
	
	public List<ChatModel> findById(long id);
}






