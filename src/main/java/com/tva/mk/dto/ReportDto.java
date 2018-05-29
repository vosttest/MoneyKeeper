package com.tva.mk.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "amount")
	private Float amount;

	@JsonProperty(value = "startDate")
	private Date startDate;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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