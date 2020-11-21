package com.onpassive.onet.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.onpassive.onet.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Events extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long eventId;
	
	@Column(name = "title", length = 2000)
	private String title;
	@Column(name = "information", length = 2000)
	private String information;
	@Column(name = "event_image")
	private String eventImage;
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name ="event_created_date")
	private String eventCreatedDate;	
	@Column(name = "event_start_date")
	private LocalDateTime eventStartDate;	
	@Column(name = "event_end_date")
	private LocalDateTime eventEndDate;
	
	@Column(name = "status", columnDefinition = "varchar(255) default 'unread'")
	private String status;
	
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_pf_pic")
	private String userPicName;
	@Column(name = "user_pf_path")
	private String pfPath;
	@Column(name = "user_first_name")
	private String firstName;

}
