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
@Table(name = "se",schema="mygroup", catalog="mygroup")
public class SEModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="se_id")
	private String se_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_no")
	private String phone_no;
	
	@Column(name="profile_pic")
	private String profile_pic;

	public String getSe_id() {
		return se_id;
	}

	public void setSe_id(String se_id) {
		this.se_id = se_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	@Override
	public String toString() {
		return "SEModel [se_id=" + se_id + ", email=" + email + ", name=" + name + ", phone_no=" + phone_no
				+ ", profile_pic=" + profile_pic + "]";
	}
}