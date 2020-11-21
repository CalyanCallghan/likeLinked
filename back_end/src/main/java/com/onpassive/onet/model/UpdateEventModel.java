package com.onpassive.onet.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventModel {
	private long eventId;
	private String title;
	private String info;
	private String eventCreatedDate;
	private String eventStartDate;
	private String eventEndDate;
	private String eventImage;
	private String imagePath;
	private String pfPath;
	private String email;
	private String edit_details;
}
