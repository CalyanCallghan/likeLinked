package com.onpassive.onet.util;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.onpassive.onet.model.EventModel;

import lombok.Data;

@Data
public class EventResponseDetails {
	HttpStatus status;
	private String message;
	private List<EventModel> eventDetails;

	public EventResponseDetails(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public EventResponseDetails(HttpStatus status, String message, List<EventModel> eventDetails) {
		this.status = status;
		this.message = message;
		this.eventDetails = eventDetails;
	}
}
