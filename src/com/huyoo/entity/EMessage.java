package com.huyoo.entity;

public class EMessage {
	//消息来源
	private int source;
	//消息来源类型，公会或者个人
	private String sourceType;
	//消息目标
	private int target;
	//消息目标类型，公会或者个人
	private String targetType;
	//处理结果，同意或者拒绝
	private String result;
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
