package com.onpassive.onet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	
	public List<User> findAll();
		
	@Query("SELECT new com.onpassive.onet.model.UserDetails(u.email, u.fileName, u.firstName, u.lastName, u.groupId) FROM User u where u.email=?1")
	UserDetails findUserByEmailId(String firstName);

	public Object findByEmpId(Long empId);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE from user where emp_id= :empId", nativeQuery=true)
	public void deleteUser(@Param("empId") Long empId);
	
	@Query(value="select u.email as 'Email ID',u.first_name,u.last_name, g.desg_desc as 'Group' "
			+ ",u.emp_id as 'EmpId',u.phone_no as 'Contact No' from user u inner join designation g on u.group_id = g.desg_id", nativeQuery=true)
	public List<Object[]> findAllUser();
	
	User findByEmpId(long empId);

	}
