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
@Table(name = "sse",schema="mygroup", catalog="mygroup")
public class SSEModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="sse_id")
	private String sse_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_no")
	private String phone_no;
	
	@Column(name="profile_pic")
	private String profile_pic;

	public String getSse_id() {
		return sse_id;
	}

	public void setSse_id(String sse_id) {
		this.sse_id = sse_id;
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
		return "SSEModel [sse_id=" + sse_id + ", email=" + email + ", name=" + name + ", phone_no=" + phone_no
				+ ", profile_pic=" + profile_pic + "]";
	}
}

	