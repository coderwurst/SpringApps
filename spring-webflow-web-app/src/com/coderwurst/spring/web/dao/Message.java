package com.coderwurst.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 5549725191072961358L;

	@Id
	@GeneratedValue
	private int id;
	
	private String subject;
	private String content;
	
	// name of user sending message
	private String name;
	
	// senders email address
	private String email;
	
	// send message to this user
	private String username;
	
	public Message () {
		// default - used by Spring

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Message(String subject, String content, String name, String email, String username) {
		// id generated automatically
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.username = username;
	}
	
	

}
