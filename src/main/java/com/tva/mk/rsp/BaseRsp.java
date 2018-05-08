package com.tva.mk.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tva.mk.common.Constants;

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
		this.status = Constants.STATUS_SUCCESS;
		this.message = "";
	}

	public BaseRsp(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public void setError(String message) {
		this.status = Constants.STATUS_ERROR;
		this.message = message;
	}

	// end
}