package com.onpassive.onet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;	
	
	@Column(name ="send_from")
	private String from;
	
	@Column(name ="sendTo")
	private String sendTo;
	
	@Column(name ="message",length = 2000)
	private String message;
	
	@Column(name ="sendDate")
	private String sendDate;	
	
	@Column(name ="status",columnDefinition = "varchar(255) default 'unread'")
	private String status;
	
	
}
