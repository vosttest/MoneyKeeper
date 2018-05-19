package com.tva.mk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "amount")
	private String amount;

	@JsonProperty(value = "createOn1")
	private String createOn1;

	@JsonProperty(value = "createOn2")
	private String createOn2;

	@JsonProperty(value = "accountId")
	private int accountId;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCreateOn1() {
		return createOn1;
	}

	public void setCreateOn1(String createOn1) {
		this.createOn1 = createOn1;
	}

	public String getCreateOn2() {
		return createOn2;
	}

	public void setCreateOn2(String createOn2) {
		this.createOn2 = createOn2;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	// end

	// region -- Methods --

	public ReportDto() {

	}

	// end
}