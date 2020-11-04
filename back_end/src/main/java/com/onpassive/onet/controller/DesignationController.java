package com.onpassive.onet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Designation;
import com.onpassive.onet.repository.DesignationRepository;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
//@CrossOrigin("*")
@RestController
@RequestMapping("/groups")
public class DesignationController {
	
	@Autowired
	private DesignationRepository designationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@GetMapping(value = "/group", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public ResponseEntity<List<Designation>> getAllDesignation() {
		try {
			logger.info("Getting All designation");
			return new ResponseEntity<List<Designation>>(designationRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error While Getting data");
			return new ResponseEntity<List<Designation>>(HttpStatus.BAD_REQUEST);
		}

	}


}
