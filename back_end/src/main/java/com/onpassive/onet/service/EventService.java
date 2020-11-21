package com.onpassive.onet.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.entity.Events;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.EventModel;
import com.onpassive.onet.model.UpdateEventModel;

public interface EventService {
	public List<Events> getAllEvents();	
	
	public long saveEvent(long empId,MultipartFile file, EventModel model);
	
	public long updateEvent(long empId,MultipartFile file,long eventId, UpdateEventModel model);
	
	public User findUserByEmpId(long empId);
	
	public long getTotalCount(); 
	
	public long countByStatus(String status);
	
	public int updateAsUnread(String status,long noticationId);
	
	public int updateEvents(String status,long id);
	
	public Events eventUpdate(Long eventId,EventModel model); 
	
	public List<EventModel> findLiveEvents();
	
	public  List<EventModel> getArchiveEvent();
	
	public  List<EventModel> getFutureEvents();
	
	public List<EventModel> displayTenEvents();
	
	
	

	
}
	
	

