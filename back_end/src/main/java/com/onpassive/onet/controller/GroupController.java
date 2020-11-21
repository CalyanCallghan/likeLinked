package com.onpassive.onet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.onet.entity.Group;
import com.onpassive.onet.exception.GroupNotFoundException;
import com.onpassive.onet.model.GroupModel;
import com.onpassive.onet.repository.GroupRepository;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.GroupService;


@RestController
@RequestMapping("/groups")
public class GroupController {

	@Autowired
	private GroupService groupService;
	@Autowired
	GroupRepository grouprepository;
	@Autowired
	UserRepository userRepository;

	//private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@GetMapping(value = "/group", produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public ResponseEntity<List<Group>> getAllGroups() {
		try {
			//logger.info("Getting All Group");
			return new ResponseEntity<List<Group>>(groupService.getGroup(), HttpStatus.OK);
		} catch (Exception e) {
			//logger.error("Error While Getting data");
			return new ResponseEntity<List<Group>>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/postgroup")
	public @ResponseBody ResponseEntity<Group> postGroup(@RequestBody Group group) {
		try {
			//logger.info(("Entered value----" + group.getGroupName()));
			List<Group> grouplist = null;
			grouplist = groupService.getGroup();

			for (Group g : grouplist) {
				//logger.info("Data From db " + g.getGroupName());
				if (g.getGroupName().equalsIgnoreCase(group.getGroupName())) {
					return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);

				} else {
					return new ResponseEntity<Group>(groupService.save(group), HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Group>(grouprepository.save(group), HttpStatus.OK);
	}

	@GetMapping(value = "/groupById/{id}")
	public List<GroupModel> getAllGroupsByID(@PathVariable("id") Integer id) {

		List<GroupModel> groupModel = grouprepository.findByGroupId(id);
		System.out.println(groupModel.toString());
		return groupModel;

	}

	@DeleteMapping("/removeGroup/{id}")
	public ResponseEntity<String> deleteGroup(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			groupService.deleteGroup(id);
			resp = new ResponseEntity<String>("Group" + id + "deleted", HttpStatus.OK);
		} catch (GroupNotFoundException grfe) {
			throw grfe; // re-throw exception to handler
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to Delete Group", HttpStatus.INTERNAL_SERVER_ERROR); // 500-ISE
		}

		return resp;
	}

}
