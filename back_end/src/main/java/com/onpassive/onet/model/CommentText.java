package com.onpassive.onet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class CommentText {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="comment")
	private String Comment;
	
	public Integer getId() {
		return id;
}
	public void setId(Integer id) {
	this.id = id;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	@Override
	public String toString() {
		return "CommentText [id=" + id + ", Comment=" + Comment + "]";
	}
}
