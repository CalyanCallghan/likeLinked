package com.onpassive.onet.model;

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
@Table(name = "sse")
@NoArgsConstructor
public class SSEModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sse_id")
	private Integer id;
	@Column(name = "sse_email")
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "profile_pic")
	private String profilePic;

}

	