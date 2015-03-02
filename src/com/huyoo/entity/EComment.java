package com.huyoo.entity;

public class EComment {
	private int id;
	private int personId;
	private long time;
	private String content;
	private int invitationId;
	private int commentIdTo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
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
	public int getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(int invitationId) {
		this.invitationId = invitationId;
	}
	public int getCommentIdTo() {
		return commentIdTo;
	}
	public void setCommentIdTo(int commentIdTo) {
		this.commentIdTo = commentIdTo;
	}
	
}
