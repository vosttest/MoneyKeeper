package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonReq {
	// region -- Fields --

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "value")
	private String value;

	// end

	// region -- Get set --

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// end

	// region -- Methods --

	public CommonReq() {
	}

	public CommonReq(String type, String value) {
		this.type = type;
		this.value = value;
	}

	// end
}