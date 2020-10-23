package com.onpassive.onet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.model.Designation;
import com.onpassive.onet.repository.DesignationRepository;
import com.onpassive.onet.repository.TLRepository;

@RestController
@RequestMapping("/groups")
public class DesignationController {
	@Autowired
	private DesignationRepository designationRepository;
	@Autowired
	private TLRepository tlRepository;

	@GetMapping("/group")
	public List<Designation> getAllDesignation() {
		return designationRepository.findAll();
	}

}
