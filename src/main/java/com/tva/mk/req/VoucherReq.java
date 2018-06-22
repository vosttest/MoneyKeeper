package com.tva.mk.req;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "accountId")
	private Integer accountId;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "total")
	private Float total;

	@JsonProperty(value = "transferFee")
	private Float transferFee;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "payee")
	private String payee;

	@JsonProperty(value = "payer")
	private String payer;

	@JsonProperty(value = "toAccount")
	private Integer toAccount;

	@JsonProperty(value = "category")
	private String category;

	@JsonProperty(value = "startDate")
	private Date startDate;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Float getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(Float transferFee) {
		this.transferFee = transferFee;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// end

	// region -- Methods --

	public VoucherReq() {
	}

	// end
}