package com.chronoswood.doublechoose.model;


public class Result<T> {
	
	private int status;
	private String msg;
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<>(Message.SUCCESS, data);
	}
	
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(Message codeMsg){
		return new Result<>(codeMsg);
	}
	
	private Result(T data) {
		this.data = data;
	}
	
	private Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public Result(Message codeMsg, T data) {
		if(codeMsg != null) {
			this.status = codeMsg.getCode();
			this.msg = codeMsg.getMessage();
		}
		this.data = data;
	}

	private Result(Message codeMsg) {
		if(codeMsg != null) {
			this.status = codeMsg.getCode();
			this.msg = codeMsg.getMessage();
		}
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int code) {
		this.status = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
