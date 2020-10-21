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
@Table(name = "designation",schema="admin", catalog="admin")
public class GroupModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="desg_id")
	private String desg_id;
	
	@Column(name="desg_desc")
	private String desg_desc;
	
	public String getDesg_id() {
		return desg_id;
	}
	public void setDesg_id(String desg_id) {
		this.desg_id = desg_id;
	}
	public String getDesg_desc() {
		return desg_desc;
	}
	public void setDesg_desc(String desg_desc) {
		this.desg_desc = desg_desc;
	}

	@Override
	public String toString() {
		return "GroupModel [desg_id=" + desg_id + ", desg_desc=" + desg_desc + "]";
	}

}
