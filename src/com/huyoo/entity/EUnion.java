package com.huyoo.entity;

import java.io.Serializable;

/**
 *工会实体类
 */
public class EUnion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 752411840091735415L;
	private int id;
	private String name;
	private int chairmanId;
	private int levelId;
	private String type;
	private int totalNum;
	private String icon;
	private long time;
	private int currentExp;
	private int activityNum;
	private String status;
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
	public int getChairmanId() {
		return chairmanId;
	}
	public void setChairmanId(int chairmanId) {
		this.chairmanId = chairmanId;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCurrentExp() {
		return currentExp;
	}
	public void setCurrentExp(int currentExp) {
		this.currentExp = currentExp;
	}
	public int getActivityNum() {
		return activityNum;
	}
	public void setActivityNum(int activityNum) {
		this.activityNum = activityNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
