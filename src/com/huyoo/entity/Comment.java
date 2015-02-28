package com.huyoo.entity;

public class Comment {
	private int id;
	
	private Person from;
	
	private Person to;
	
	private long time;
	
	private String content;
	
	private Invitation invitation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getFrom() {
		return from;
	}

	public void setFrom(Person from) {
		this.from = from;
	}

	public Person getTo() {
		return to;
	}

	public void setTo(Person to) {
		this.to = to;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	
	
}
