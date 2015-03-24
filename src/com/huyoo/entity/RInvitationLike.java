package com.huyoo.entity;

public class RInvitationLike {
	private int id;
	private int invitationId;
	private int personId;
	private long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(int invitationId) {
		this.invitationId = invitationId;
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
	
}
