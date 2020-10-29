package com.onpassive.onet.entity;

import java.time.LocalDateTime;

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
@Table(name="post")
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "file_Path")
	private String filePath;
	@Column(name = "file_Name")
	private String fileName;
	@Column( name="description",length = 300)
	private String description;
	@Column(name="type")
	private String type;
	@Column(name="group_id")
	private Integer groupId;
	@Column(name = "created_dt")
	private LocalDateTime createdDt;
	@Column(name = "format")
	private String format;
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", createdBy=" + createdBy + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", description=" + description + ", type=" + type + ", groupId=" + groupId + ", createdDt="
				+ createdDt + "]";
	}
	

	
	
}
