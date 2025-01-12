package com.in4c.blogApp.model.helper;

public class Result<T> {
	
	private boolean success;
	private T data;
	private String message;

	public Result(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean error) {
		this.success = error;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}