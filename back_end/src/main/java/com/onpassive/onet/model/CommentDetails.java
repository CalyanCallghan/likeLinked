package com.onpassive.onet.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetails {
	private int commentId;
	private String empId;
	private String content;	
}

