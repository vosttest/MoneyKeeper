package com.tva.mk.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tva.mk.model.VoucherDetail;

public class VoucherDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

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

	@JsonProperty(value = "startDate")
	private Date startDate;

	@JsonProperty(value = "startDate")
	private List<VoucherDetail> voucherDetail;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<VoucherDetail> getVoucherDetail() {
		return voucherDetail;
	}

	public void setVoucherDetail(List<VoucherDetail> voucherDetail) {
		this.voucherDetail = voucherDetail;
	}

	// end

	// region -- Methods --

	public VoucherDto() {

	}

	// end
}