package com.onpassive.onet.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onpassive.onet.entity.Events;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.EventModel;
import com.onpassive.onet.model.UpdateEventModel;
import com.onpassive.onet.service.EventService;
import com.onpassive.onet.util.EventResponseDetails;

/**
 * 
 * @author Preeti Kumari
 *
 */
@RestController
@RequestMapping("/event")
public class EventsController {
	//private static final Logger logger = LoggerFactory.getLogger(EventsController.class);
	@Autowired
	EventService eventsService;

	/**
	 * get the all events
	 * 
	 * @return list of all Events
	 * 
	 */
	@GetMapping("/getAllEvents")
	public ResponseEntity<List<Events>> getAllEvents() {
		//logger.info("----------Getting all events list----------");
		return ResponseEntity.ok(eventsService.getAllEvents());

	}

	/**
	 * get all live events
	 * 
	 * @return List of Live events
	 */

	@GetMapping("/getLiveEvents")
	public ResponseEntity<List<EventModel>> getLiveEvent() {
		String message = "";
		try {
			List<EventModel> liveEvents = new ArrayList<>();
			liveEvents = eventsService.findLiveEvents();
			if (liveEvents.size() > 0) {
				message = "OK";
//				return new ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message, liveEvents));
				return new ResponseEntity<List<EventModel>>(liveEvents, HttpStatus.OK);
			}
			message = "No Live Events";
//			return ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message));
			return new ResponseEntity<List<EventModel>>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			message = "No Live Events";
//			return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));
			return new ResponseEntity<List<EventModel>>(HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * get all Future events
	 * 
	 * @return List of Future events
	 * 
	 */

	@GetMapping("/getFutureEvents")
	public ResponseEntity<List<EventModel>> getFutureEvents() {
		String message = "";
		try {
			List<EventModel> futureEvents = new ArrayList<>();
			futureEvents = eventsService.getFutureEvents();
			if (futureEvents.size() > 0) {
				message = "OK";
//				return ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message, futureEvents));
				return new ResponseEntity<List<EventModel>>(futureEvents, HttpStatus.OK);
			}
			message = "No Future Events";
//			return ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message));
			return new ResponseEntity<List<EventModel>>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			message = "No Future Events";
//			return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));
			return new ResponseEntity<List<EventModel>>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * get all Archive events
	 * 
	 * @return List of Archive events
	 * 
	 */

	@GetMapping("/getArchievedEvents")
	public List<EventModel> getArchiveEvents() {
		return eventsService.getArchiveEvent();
	}

	/**
	 * create the Event
	 * 
	 * @return list of all Events
	 * 
	 */
	@PostMapping("/createEvent/{empId}")
	public ResponseEntity<EventResponseDetails> createEvent(@PathVariable long empId,
			@RequestParam("image") MultipartFile[] files, @RequestParam("data") String data) {

		User user = eventsService.findUserByEmpId(empId);
		System.out.println(user);
		String message = "";
		long eventId;
		if (user.getRole() != null && user.getRole().equalsIgnoreCase("admin")) {
			try {
				ObjectMapper obj = new ObjectMapper();

				EventModel model = obj.readValue(data, EventModel.class);
				eventId = eventsService.saveEvent(empId, files[0], model);
				message = "Uploaded the image successfully."; // all events
//				event = eventsService.getAllEvents();
				return ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message));
			} catch (Exception e) {
				message = "Fail";
				//logger.error("error occured at uploadFile---->" + e.getMessage());
				return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));
			}
		}
		message = "Not Admin";
		return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));

	}

	/**
	 * get the total nos of notification
	 * 
	 * @return Count of Messages
	 * 
	 */
	@GetMapping("/totalCount")
	public long getTotalCount() {
		long totalCount = 0;
		totalCount = eventsService.getTotalCount();
		return totalCount;
	}

	/**
	 * get the total nos of unread notification
	 * 
	 * @param status
	 * @return Count of unread messages
	 * 
	 */
	@GetMapping("/unreadCount")
	public long getUnreadCount() {
		long unreadCount = 0;
		unreadCount = eventsService.countByStatus("unread");
		return unreadCount;
	}

	/**
	 * update the read to unread
	 * 
	 * @param notificationId
	 * @return Count of unread messages
	 * 
	 */
	@PutMapping("notificationUnread/{notificationId}")
	public int notificationReadByUser(@PathVariable("notificationId") long noticationId,
			@RequestParam("status") String status) {
		int count = eventsService.updateAsUnread(status, noticationId);
		return count;
	}

	/**
	 * update the events
	 * 
	 * @param eventId
	 * @return updated events
	 * 
	 */

	@PutMapping("/updateEvent/{empId}/event/{eventId}")
	public ResponseEntity<EventResponseDetails> updateEmployee(@PathVariable long empId, @PathVariable long eventId,
			@RequestParam("image") MultipartFile[] files, @RequestParam("data") String data) {

		User user = eventsService.findUserByEmpId(empId);
		System.out.println(user);
		String message = "";
//		long eventId;
		if (user.getRole() != null && user.getRole().equalsIgnoreCase("admin")) {
			try {
				ObjectMapper obj = new ObjectMapper();

				UpdateEventModel model = obj.readValue(data, UpdateEventModel.class);
				eventId = eventsService.updateEvent(empId, files[0], eventId, model);
				message = "Uploaded the image successfully."; // all events
//				event = eventsService.getAllEvents();
				return ResponseEntity.ok(new EventResponseDetails(HttpStatus.OK, message));
			} catch (Exception e) {
				message = "Fail";
				//logger.error("error occured at uploadFile---->" + e.getMessage());
				return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));
			}
		}
		message = "Not Admin";
		return ResponseEntity.ok(new EventResponseDetails(HttpStatus.EXPECTATION_FAILED, message));

//		return ResponseEntity.ok(eventsService.eventUpdate(eventId, model));
	}

	@GetMapping("/displayTenEvents")
	public ResponseEntity<List<EventModel>> displayTenEvents() {
		return ResponseEntity.ok(eventsService.displayTenEvents());

	}

}
