package com.huyoo.entity;

public class RPersonAchievement {
	private int id;
	private int personId;
	private int achievementId;
	private long time;
	private int currentProgress;
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
	public int getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCurrentProgress() {
		return currentProgress;
	}
	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}
	
}
