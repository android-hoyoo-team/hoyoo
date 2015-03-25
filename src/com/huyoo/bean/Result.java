package com.huyoo.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Result<T> {

	@Expose
	private String status;
	@Expose
	private String message;
	@Expose
	private T result;
	
	
	
	public Result() {
		super();
	}
	public Result(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Result(String status, String message, T result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String toJson()
	{
		Gson gson = new Gson(); 
		String jsonStr = null;  
		if (gson != null) {  
			jsonStr = gson.toJson(this);  
		}  
		return jsonStr;
	}
	@Override
	public String toString() {
		return "Result [message=" + message + ", status=" + status
				+ ", result=" + result + "]";
	}
}
