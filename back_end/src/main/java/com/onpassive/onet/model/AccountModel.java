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
@Table(name = "accounts")
public class AccountModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="acc_id")
	private String acc_id;
	
	  @Column(name="acc_email") private String email;
	  
		
		  @Column(name="name") private String name;
		  
			
			  @Column(name="phone_no") private String phone_no;
			  
			  @Column(name="profile_pic") private String profile_pic;
			 
	 

	


	
}
