package com.onpassive.onet.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetails {
	private int id;
	private String firstName;
	private String lastName;
	private String profileImage;
	private String content;	
	private String when;
	private long likeCount;
	private long commentCount;
}

