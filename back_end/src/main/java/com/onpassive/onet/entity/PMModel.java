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
@Entity
@Table(name = "pm")
@NoArgsConstructor
public class PMModel extends AuditModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pm_id")
	private Integer id;
	@Column(name = "pm_email")
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "profile_pic")
	private String profilePic;

}
