package com.huyoo.entity;

import java.io.Serializable;

public class EInvitation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4185529794547215472L;
	private int id;
	private int personId;
	private long activityTime;
	private String title;
	private String content;
	private String address;
	private int maxNum;
	private int currentNum;
	private int forwardIdFrom;
	private int originalId;
	private String status;
	private long issueTime;
	private String icons;
	private int hits;
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
	public long getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(long activityTime) {
		this.activityTime = activityTime;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public int getForwardIdFrom() {
		return forwardIdFrom;
	}
	public void setForwardIdFrom(int forwardIdFrom) {
		this.forwardIdFrom = forwardIdFrom;
	}
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(long issueTime) {
		this.issueTime = issueTime;
	}
	public String getIcons() {
		return icons;
	}
	public void setIcons(String icons) {
		this.icons = icons;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
}
