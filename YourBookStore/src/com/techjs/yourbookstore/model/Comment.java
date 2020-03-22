package com.techjs.yourbookstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")

public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "CONTENT", length = 255, nullable = false)
	private String content;
	
	@Column(name = "POSTED_ON",nullable = false)
	private Date postedOn;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSTED_BY")
	private User postedBy;
	
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", postedOn=" + postedOn + ", postedBy=" + postedBy + "]";
	}
	
}
