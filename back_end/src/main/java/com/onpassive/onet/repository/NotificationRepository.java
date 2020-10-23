package com.onpassive.onet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.onpassive.onet.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	//for all notifications
	public List<Notification> findAll();
	
	//count of unread notifications
	long countByStatus(String status);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update notifications u set u.status=?1 where u.id = ?2",nativeQuery = true)
	int updateAsUnread(String status,long id);
	

}
