package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseReq {
	// region -- Fields --

	@JsonProperty(value = "userId")
	private Integer userId;

	@JsonProperty(value = "keyWord")
	private String keyWord;

	// end

	// region -- Get set --

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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

	public BaseReq(String keyWord, Integer userId) {
		this.userId = userId;
		this.keyWord = keyWord;
	}

	// end
}