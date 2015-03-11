package com.huyoo.entity;

public class RUnionPerson {
	private int id;
	private int unionId;
	private int personId;
	private long time;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
