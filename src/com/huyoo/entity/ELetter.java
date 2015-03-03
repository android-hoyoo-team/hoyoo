package com.huyoo.entity;

import java.io.Serializable;

public class ELetter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3896479489057896238L;

	private int id;
	
	private int personIdFrom;
	
	private int personIdTo;
	
	private String title;
	
	private String content;
	
	private long time;
	
	private String statusFrom;
	
	private String statusTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonIdFrom() {
		return personIdFrom;
	}

	public void setPersonIdFrom(int personIdFrom) {
		this.personIdFrom = personIdFrom;
	}

	public int getPersonIdTo() {
		return personIdTo;
	}

	public void setPersonIdTo(int personIdTo) {
		this.personIdTo = personIdTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getStatusFrom() {
		return statusFrom;
	}

	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}

	public String getStatusTo() {
		return statusTo;
	}

	public void setStatusTo(String statusTo) {
		this.statusTo = statusTo;
	}

}
