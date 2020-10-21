package com.onpassive.onet.model;

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
@Table(name="post", schema="admin", catalog = "admin")
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	//private Integer postId;
	@Column(name = "email")
	private String email;
	@Column(name = "file_Path")
	private String filePath;
	@Column(name = "file_Name")
	private String fileName;
	@Column( name="content",length = 300)
	private String content;
	@Column(name="type")
	private String type;
	@Override
	public String toString() {
		return "Post [id=" + id + ", email=" + email + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", content=" + content + ", type=" + type + "]";
	}
	
	
}
