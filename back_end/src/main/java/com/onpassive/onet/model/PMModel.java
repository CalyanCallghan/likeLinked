package com.onpassive.onet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "pm",schema = "opnetdb")
public class PMModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="pm_id")
	private String pm_id;
	
	
	  @Column(name="pm_email") private String email;
	  
	   @Column(name="pm_name") private String name;
	  
		
		  @Column(name="phone_no") private String phone_no;
		  
		  @Column(name="profile_pic") private String profile_pic;
		 

	
}
