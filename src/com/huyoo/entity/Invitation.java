package com.huyoo.entity;

public class Invitation {
	//序列号，主键
	private int id;
	//邀请的id,原创邀请和转发邀请的id相同
	private int invitationId;
	//邀请人,如果是原创的，为发布者；如果是转发的，为转发者
	private Person inviter;
	//邀请来自于，如果是原创邀请，设置为null;
	private Person invitationFrom;
	
	private String title;
	
	private String content;
	
	private String imageUrl;

	//参与人数
	private int participantsNum;
	//发布时间
	private long releaseTime;
	//活动开始时间
	private long startTime;
	//活动结束时间
	private long endTime;
	//活动地点
	private String address;
	//活动联系方式(手机号)
	private String contactWay;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getInviter() {
		return inviter;
	}

	public void setInviter(Person inviter) {
		this.inviter = inviter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return content;
	}

	public void setDiscription(String discription) {
		this.content = discription;
	}

	public int getParticipantsNum() {
		return participantsNum;
	}

	public void setParticipantsNum(int participantsNum) {
		this.participantsNum = participantsNum;
	}

	public long getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public int getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(int invitationId) {
		this.invitationId = invitationId;
	}

	public Person getInvitationFrom() {
		return invitationFrom;
	}

	public void setInvitationFrom(Person invitationFrom) {
		this.invitationFrom = invitationFrom;
	}
	
}
