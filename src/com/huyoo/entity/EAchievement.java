package com.huyoo.entity;

import java.io.Serializable;

public class EAchievement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3786417390498109919L;
	private int id;
	private String name;
	private int exp;
	private String type;
	private String description;
	private String icon;
	private String addition;
	private int totalProgress;
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
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public int getTotalProgress() {
		return totalProgress;
	}
	public void setTotalProgress(int totalProgress) {
		this.totalProgress = totalProgress;
	}
	
}
