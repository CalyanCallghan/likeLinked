package com.onpassive.onet.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
	private String file_name;
	private String firstName;
	private String lastName;
	private int group_id;
	private long empId;
	private String email;
	private String phoneNo;
	private String groupName;
	
	public UserDetails(String email, String file_name, String first_name, String last_name, int group_id) {
		this.email = email;
		this.file_name = file_name;
		this.firstName = first_name;
		this.lastName = last_name;
		this.group_id = group_id;
	}

	public UserDetails(String email, String first_name, String last_name, String groupName, long emp_id,String phoneNo) {
		this.email = email;
		this.firstName = first_name;
		this.lastName = last_name;
		this.groupName = groupName;
		this.empId = emp_id;
		this.phoneNo = phoneNo;
	}
}

