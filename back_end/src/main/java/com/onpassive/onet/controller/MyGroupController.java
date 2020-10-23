package com.onpassive.onet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.model.AccountModel;
import com.onpassive.onet.model.GroupModel;
import com.onpassive.onet.model.PMModel;
import com.onpassive.onet.model.SEModel;
import com.onpassive.onet.model.SSEModel;
import com.onpassive.onet.model.TLModel;
import com.onpassive.onet.repository.AccountsRepository;
import com.onpassive.onet.repository.PMRepository;
import com.onpassive.onet.repository.SERepository;
import com.onpassive.onet.repository.SSERepository;
import com.onpassive.onet.repository.TLRepository;

@RestController
@RequestMapping("/myGroup")
public class MyGroupController {
	
	 @Autowired
	 private TLRepository tlRepository ;
	 @Autowired
	 private PMRepository pmRepository ;
	 @Autowired
	 private SERepository seRepository;
	 @Autowired
	 private SSERepository sseRepository;
	 @Autowired
	 private AccountsRepository accountsRepository;
	 @GetMapping("/tl")
      public List<TLModel> getAllTLList() {
		List<TLModel>  TLmodel = new ArrayList<TLModel>();
		TLmodel = tlRepository.findAll();
		System.out.print("::: "+TLmodel.get(0).getName());
		
        return TLmodel;
    }
	  @GetMapping("/pm")
	    public List<PMModel> getAllDesignation() {
		 
	        return pmRepository.findAll();
	    }
	  
	  @GetMapping("/sse")
	    public List<SSEModel> getAllSSEList() {
		 
	        return sseRepository.findAll();
	    }
	  @GetMapping("/se")
	    public List<SEModel> getAllSEList() {
		 
	        return seRepository.findAll();
	    }
	  @GetMapping("/accounts")
	    public List<AccountModel> getAllAccountsList() {
		 
	        return accountsRepository.findAll();
	    }
}
