package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountReq {
	// region -- Fields --

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "description")
	private String description;

	// end

	// region -- Get set --

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// end

	// region -- Methods --

	public AccountReq() {
	}

	// end
}