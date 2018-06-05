package com.tva.mk.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "balance")
	private double balance;

	@JsonProperty(value = "currency")
	private String currency;

	@JsonProperty(value = "accountDto")
	private List<Object[]> accountDto;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Object[]> getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(List<Object[]> accountDto) {
		this.accountDto = accountDto;
	}
	// end

	// region -- Methods --

	public AccountDto() {

	}

	// end
}