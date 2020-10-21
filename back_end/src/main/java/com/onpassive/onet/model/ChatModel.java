package com.onpassive.onet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "chatmessage",schema="admin", catalog="admin")
public class ChatModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "message_from", columnDefinition = "varchar(255) default 'preeti'")
	private String messageFrom;

	@Column(name = "message_to", columnDefinition = "varchar(255) default 'preeti'")
	private String messageTo;
	
	@Column(name = "message_text", columnDefinition = "varchar(2000)")
	private String messagetext;
	
	@Column(name = "sendDate")
	private String sendDate;

	@Column(name ="status", columnDefinition = "varchar(255) default 'unread'")
	private String status;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}

}
