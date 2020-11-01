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
	private String firstName;
	private String lastName;
	private String userProfile;
}
