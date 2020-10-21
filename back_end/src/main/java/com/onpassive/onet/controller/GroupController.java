package com.onpassive.onet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.model.GroupModel;
import com.onpassive.onet.model.TLModel;
import com.onpassive.onet.repository.GroupRepository;
import com.onpassive.onet.repository.TLRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/groups")
public class GroupController {
	 @Autowired
	 private GroupRepository grouprepository;
	 @Autowired
	 private TLRepository tlRepository ;
	 
	  @GetMapping("/group")
	    public List<GroupModel> getAllDesignation() {
		 
	        return grouprepository.findAll();
	    }
	 
//	  @GetMapping("/tl")
//	    public List<TLModel> getAllTLList() {
//		    List list =tlRepository.findAll();
//	        return list;
//	    }
}
