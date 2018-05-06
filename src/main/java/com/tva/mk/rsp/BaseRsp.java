package com.tva.mk.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRsp {
	// region -- Fields --

	@JsonProperty(value = "callStatus")
	private String callStatus;

	@JsonProperty(value = "message")
	private String message;

	// end

	// region -- Get set --

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// end

	// region -- Methods --

	public BaseRsp() {
		this.callStatus = "success";
		this.message = "";
	}

	public BaseRsp(String callStatus, String message) {
		this.callStatus = callStatus;
		this.message = message;
	}

	// end
}