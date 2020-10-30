package com.onpassive.onet.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
	private String email;
	private String file_name;
	private String first_name;
	private String last_name;
	private int group_id;
}

