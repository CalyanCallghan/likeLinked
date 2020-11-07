package com.onpassive.onet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Notification;
import com.onpassive.onet.repository.NotificationRepository;
import com.onpassive.onet.service.NotificationService;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
//@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationService NotificationService;

	@GetMapping("/getNotifications")
	public List<Notification> getNotifications() {
		logger.info("-------getNotifications------start-");
		return NotificationService.getNotifications();
		
	}

	// Getting the all List of count
	@GetMapping("/totalCount")
	public long getTotalCount() {
		logger.info("-------getTotalCount------start");
		long totalCount = 0;
		totalCount = notificationRepository.findAll().size();
		logger.info("-------getTotalCount------end");

		return totalCount;
	}

	// Getting the count of unread message
	@GetMapping("/unreadCount")
	public long getUnreadCount() {
		logger.info("-------getUnreadCount------start");
		long unreadCount = 0;
		unreadCount = notificationRepository.countByStatus("unread");
		logger.info("-------getUnreadCount------end");
		return unreadCount;
	}

	// update unreadCount
	@PutMapping("notificationUnread/{notificationId}")
	public int notificationReadByUser(@PathVariable("notificationId") long id, @RequestParam("status") String status) {
		logger.info("-------notificationReadByUser------>"+id+"<---->"+status);
		int count = notificationRepository.updateAsUnread(status, id);
		logger.info("-------notificationReadByUser------end");
		return count;
	}

}
