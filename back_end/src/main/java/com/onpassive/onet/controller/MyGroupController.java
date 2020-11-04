package com.onpassive.onet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.AccountModel;
import com.onpassive.onet.entity.PMModel;
import com.onpassive.onet.entity.SEModel;
import com.onpassive.onet.entity.SSEModel;
import com.onpassive.onet.entity.TLModel;
import com.onpassive.onet.repository.AccountsRepository;
import com.onpassive.onet.repository.PMRepository;
import com.onpassive.onet.repository.SERepository;
import com.onpassive.onet.repository.SSERepository;
import com.onpassive.onet.repository.TLRepository;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
//@CrossOrigin("*")
@RestController
@RequestMapping("/myGroup")
public class MyGroupController {
	private static final Logger logger = LoggerFactory.getLogger(MyGroupController.class);

	@Autowired
	private TLRepository tlRepository;
	@Autowired
	private PMRepository pmRepository;
	@Autowired
	private SERepository seRepository;
	@Autowired
	private SSERepository sseRepository;
	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/tl")
	public List<TLModel> getAllTLList() {
		logger.info("--------getAllTLList------start");
		return tlRepository.findAll();
	}

	@GetMapping("/pm")
	public List<PMModel> getAllDesignation() {
		logger.info("--------getAllDesignation-----start");
		return pmRepository.findAll();
	}

	@GetMapping("/sse")
	public List<SSEModel> getAllSSEList() {
		logger.info("--------getAllSSEList------start");
		return sseRepository.findAll();
	}

	@GetMapping("/se")
	public List<SEModel> getAllSEList() {
		logger.info("--------getAllSEList-------start");
		return seRepository.findAll();
	}

	@GetMapping("/accounts")
	public List<AccountModel> getAllAccountsList() {
		logger.info("--------getAllAccountsList-------start");
		return accountsRepository.findAll();
	}
}
