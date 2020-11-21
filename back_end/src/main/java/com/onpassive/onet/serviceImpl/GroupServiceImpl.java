package com.onpassive.onet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpassive.onet.entity.Group;
import com.onpassive.onet.exception.GroupNotFoundException;
import com.onpassive.onet.repository.GroupRepository;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Group> getGroup() {

		return groupRepository.findAll();
	}

	@Override
	public Group save(Group group) {
		// TODO Auto-generated method stub
		return groupRepository.save(group);
	}

	@Override
	public void deleteGroup(Integer id) {
		Group g = getOneGroup(id);
		groupRepository.delete(g);

	}

	@Override
	public Group getOneGroup(Integer id) {
		Group g = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(
				new StringBuffer().append("Group '").append(id).append("' not exist").toString()));
		return g;
	}

}
