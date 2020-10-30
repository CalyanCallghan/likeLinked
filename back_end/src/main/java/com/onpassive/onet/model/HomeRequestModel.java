package com.onpassive.onet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeRequestModel {
	
private int createdBy;
private String content;
private String type;
private Integer groupId;
private String format;


}
