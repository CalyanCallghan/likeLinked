package com.onpassive.onet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetails {
	private int postId;
	private String fileName;
	private String postContent;
	private String postFormat;
	private String type;
	private String firstName;
	private String lastName;
	private String userProfile;
	private long likeCount;
	private long commentCount;
	private String when;

	
	public PostDetails(int postId, String fileName, String postContent, String postFormat, String type,
			String firstName, String lastName, String userProfile) {
		this.postId = postId;
		this.fileName = fileName;
		this.postContent = postContent;
		this.postFormat = postFormat;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userProfile = userProfile;
	}
}
