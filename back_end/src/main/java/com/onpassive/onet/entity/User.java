package com.onpassive.onet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.onpassive.onet.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AuditModel {
	@Id
	@Column(name = "emp_id", nullable = false, updatable = false)
	private long empId;
	@Column(name = "email")
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "pf_path")
	private String pfPath;
	@Column(name = "profile_pic_name")
	private String fileName;
	@Column(name = "group_id")
	private Integer groupId;
	@Column(name = "status")
	private boolean status;
	@Column(name = "phone_no")
	private String phoneNo;

}