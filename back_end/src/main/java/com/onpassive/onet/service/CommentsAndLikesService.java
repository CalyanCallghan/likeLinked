package com.onpassive.onet.service;

public interface CommentsAndLikesService {
	
 public long updateAndReturnLikesCountOfPost(int employeeId,int postId);
 
 public long updateAndReturnLikesCountOfComment(int employeeId,int commentId);
 
 public long updateAndReturnLikesCountOfSubComment(int employeeId,int commentId);

 
}
