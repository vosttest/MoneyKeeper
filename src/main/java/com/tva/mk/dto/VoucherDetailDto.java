package com.tva.mk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherDetailDto {
	// region -- Fields --

	@JsonProperty(value = "amount")
	private Double amount;

	@JsonProperty(value = "categoryText")
	private String categoryText;

	@JsonProperty(value = "icon")
	private String icon;

	@JsonProperty(value = "accountText")
	private String accountText;

	// end

	// region -- Get set --

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCategoryText() {
		return categoryText;
	}

	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAccountText() {
		return accountText;
	}

	public void setAccountText(String accountText) {
		this.accountText = accountText;
	}

	// end

	// region -- Methods --

	public VoucherDetailDto() {

	}

	// end
}