package com.onpassive.onet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeRequestModel {
	
private String email;
private String content;
private String type;

}
