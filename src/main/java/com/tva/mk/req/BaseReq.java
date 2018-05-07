package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReq {
	// region -- Fields --

	@JsonProperty(value = "userId")
	private Integer userId;

	@JsonProperty(value = "keyword")
	private String keyword;

	// end

	// region -- Get set --

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// end

	// region -- Methods --

	public BaseReq() {
	}

	public BaseReq(String keyword, Integer userId) {
		this.userId = userId;
		this.keyword = keyword;
	}

	// end
}