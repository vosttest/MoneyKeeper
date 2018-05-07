package com.tva.mk.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRsp {
	// region -- Fields --

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "message")
	private String message;

	// end

	// region -- Get set --

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

	// end

	// region -- Methods --

	public BaseRsp() {
		this.status = "success";
		this.message = "";
	}

	public BaseRsp(String status, String message) {
		this.status = status;
		this.message = message;
	}

	// end
}