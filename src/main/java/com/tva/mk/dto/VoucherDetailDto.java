package com.tva.mk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherDetailDto {
	// region -- Fields --

	@JsonProperty(value = "voucherId")
	private Integer voucherId;

	@JsonProperty(value = "accountId")
	private Integer accountId;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "total")
	private Double total;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "payee")
	private String payee;

	@JsonProperty(value = "payer")
	private String payer;

	@JsonProperty(value = "toAccount")
	private Integer toAccount;

	@JsonProperty(value = "userId")
	private Integer userId;

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

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Integer getToAccount() {
		return toAccount;
	}

	public void setToAccount(Integer toAccount) {
		this.toAccount = toAccount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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