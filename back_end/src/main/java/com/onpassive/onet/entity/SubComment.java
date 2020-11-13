package com.onpassive.onet.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.onpassive.onet.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sub_comment")
public class SubComment extends AuditModel implements Serializable {
	
	/*
	 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "post_id", nullable = false) private Post post;
	 */
	
	/*
	 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "comment_id", nullable = false) //@OnDelete(action =
	 * OnDeleteAction.CASCADE) private Comment comment;
	 */
    @Column(name="comment_id")
	private Integer commentId;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	
	@Column(name="content")
	private String content;
	
//	@Column(name="is_Liked")
//	private boolean isLiked;
	
	@Column(name="emp_id")
    private String empId;
	
//	 @ManyToOne(fetch = FetchType.EAGER)
//	    @JoinColumn(name = "comment_id")
//	    private Comment comment;
//	 
//	 @ManyToOne(fetch = FetchType.EAGER)
//	    @JoinColumn(name = "post_id")
//	    private Post post;


}
