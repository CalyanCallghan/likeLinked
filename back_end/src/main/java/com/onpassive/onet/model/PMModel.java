package com.onpassive.onet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//public class PMModel {
//
//}


@Entity
@Data
@Table(name = "pm",schema="mygroup", catalog="mygroup")
public class PMModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="pm_id")
	private String pm_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_no")
	private String phone_no;
	
	@Column(name="profile_pic")
	private String profile_pic;

	public String getPm_id() {
		return pm_id;
	}

	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
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
		return "PMModel [pm_id=" + pm_id + ", email=" + email + ", name=" + name + ", phone_no=" + phone_no
				+ ", profile_pic=" + profile_pic + "]";
	}
	

	
}
