package com.onpassive.onet.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.onpassive.onet.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name="post")
@NoArgsConstructor
public class Post extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private Integer id;
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
	
//	 @OneToMany(cascade = CascadeType.ALL,
//	            fetch = FetchType.EAGER,
//	            mappedBy = "post")
//	    private Set<Comment> comments = new HashSet<>();
//	 
//
//	 @OneToMany(cascade = CascadeType.ALL,
//	            fetch = FetchType.EAGER,
//	            mappedBy = "post")
//	    private Set<SubComment> subComments = new HashSet<>();
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", createdBy=" + createdBy + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", description=" + description + ", type=" + type + ", groupId=" + groupId + ", createdDt="
				+ createdDt + "]";
	}
	
}
