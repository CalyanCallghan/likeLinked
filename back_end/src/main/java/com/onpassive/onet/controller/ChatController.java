package com.onpassive.onet.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.ChatModel;
import com.onpassive.onet.repository.ChatRepository;


//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
@CrossOrigin("*")

@RequestMapping("/api")
@RestController
public class ChatController {
	@Autowired
	ChatRepository chatrepo;

	@PostMapping("/sentMessage")
	public ResponseEntity<ChatModel> sentMessage(@RequestBody ChatModel chat) {
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		chat.setSendDate(dateTimeInGMT.format(new Date()));
		
		return new ResponseEntity<ChatModel>(chatrepo.save(chat), HttpStatus.OK);
	}

//	@GetMapping("/getMessage/{id}")
//	public List<ChatModel> getMessage(@PathVariable long id) {
//		return chatrepo.findById(id);
//
//	}

}
