package com.huyoo.entity;

public class RAttention {

	private int id;
	
	private int personIdFrom;
	
	private int personIdTo;
	
	private long time;

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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}
