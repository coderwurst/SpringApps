package com.coderwurst.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.coderwurst.spring.web.validation.ValidEmail;

/*
 * to contain 1 row at a time from the offers table in DB
 */
public class Offer {

	private int id;
	
	@Size (min=5, max=100, message="name must be between 5 and 100 characters")
	private String name;
	
	@NotNull
	// @Pattern(regexp=".*\\@.*\\..*", message="this does not appear to be a valid email address")		// ./* = some text, \\@ '@' sign (escaped \), more text with '.'
	@ValidEmail(min=6, message="email address not valid")	// custom length and message
	private String email;
	
	@Size (min=20, max=255, message="text must be between 20 and 255 characters")
	private String text;

	public Offer() {
		// default constructor
	}
	
	public Offer(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}
	
	public Offer(int id, String name, String email, String text) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	}

}
