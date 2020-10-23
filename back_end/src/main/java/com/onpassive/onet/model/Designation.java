package com.onpassive.onet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "designation")
@NoArgsConstructor
public class Designation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "desg_id")
	private Integer desgId;
	@Column(name = "desg_desc")
	private String desgdesc;

}
