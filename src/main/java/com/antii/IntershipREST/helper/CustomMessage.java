package com.antii.IntershipREST.helper;

import java.io.Serializable;

public class CustomMessage implements Serializable {

	private static final long serialVersionUID = 7834625944035366265L;
	private String message;
	
	private String errCode = "200";
	
	public CustomMessage() {}
	public CustomMessage(String message, String errCode) {
		super();
		this.message = message;
		this.errCode = errCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	
}
