package com.tva.mk.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherDto {
	// region -- Fields --

	@JsonProperty(value = "startDate")
	private Date startDate;

	@JsonProperty(value = "voucherDetail")
	private List<VoucherDetailDto> voucherDetail;

	@JsonProperty(value = "totalExpense")
	private double totalExpense;

	@JsonProperty(value = "totalIncome")
	private double totalIncome;

	// end

	// region -- Get set --

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<VoucherDetailDto> getVoucherDetail() {
		return voucherDetail;
	}

	public void setVoucherDetail(List<VoucherDetailDto> voucherDetail) {
		this.voucherDetail = voucherDetail;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	// end

	// region -- Methods --

	public VoucherDto() {

	}

	// end
}