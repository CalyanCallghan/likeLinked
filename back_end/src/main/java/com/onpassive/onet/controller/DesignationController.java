package com.onpassive.onet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Designation;
import com.onpassive.onet.repository.DesignationRepository;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" })
@RestController
@RequestMapping("/groups")
public class DesignationController {
	@Autowired
	private DesignationRepository designationRepository;
	

	@GetMapping("/group")
	public List<Designation> getAllDesignation() {
		return designationRepository.findAll();
	}


}
