package com.huyoo.entity;

import java.io.Serializable;

public class ELevel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9096784576815875038L;
	private int id;
	private String name;
	private int upgradeExp;
	private String type;
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
	public int getUpgradeExp() {
		return upgradeExp;
	}
	public void setUpgradeExp(int upgradeExp) {
		this.upgradeExp = upgradeExp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
