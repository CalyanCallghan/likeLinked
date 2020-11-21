package com.onpassive.onet.service;

import java.util.List;

import com.onpassive.onet.entity.Group;

public interface GroupService {
	public List<Group> getGroup();

	public Group save(Group group);

	void deleteGroup(Integer id);

	Group getOneGroup(Integer id);
}
