package com.onpassive.onet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.model.CommentText;
import com.onpassive.onet.repository.CommentRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentRepository commentRepository;

	@PostMapping("/userComment")
	public CommentText sentMessage(@RequestBody CommentText comment) {
		return commentRepository.save(comment);
	}
	
}
