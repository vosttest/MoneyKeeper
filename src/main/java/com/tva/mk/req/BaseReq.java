package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReq {
	// region -- Fields --

	@JsonProperty(value = "keyword")
	private String keyword;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "value")
	private String value;

	// end

	// region -- Get set --

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

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

	public BaseReq() {
	}

	public BaseReq(String keyword) {
		this.keyword = keyword;
	}

	// end
}