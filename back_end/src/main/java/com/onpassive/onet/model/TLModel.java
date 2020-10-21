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
@Table(name = "tl",schema="mygroup", catalog="mygroup")
public class TLModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="tl_id")
	private String tl_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_no")
	private String phone_no;
	
	@Column(name="profile_pic")
	private String profile_pic;
	
	public String getTl_id() {
		return tl_id;
	}
	public void setTl_id(String tl_id) {
		this.tl_id = tl_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "TLModel [tl_id=" + tl_id + ", name=" + name + ", email=" + email + ", phone_no=" + phone_no
				+ ", profile_pic=" + profile_pic + "]";
	}
	
}
