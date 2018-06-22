package com.tva.mk.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tva.mk.common.Const;

public class VoucherDto {
	// region -- Fields --

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.DateTime.FULL)
	@JsonProperty(value = "startDate")
	private Date startDate;

	@JsonProperty(value = "voucherDetail")
	private List<VoucherDetailDto> voucherDetail;

	@JsonProperty(value = "totalExpense")
	private double totalExpense;

	@JsonProperty(value = "totalIncome")
	private double totalIncome;

	@JsonProperty(value = "defaultCurrency")
	private String defaultCurrency;

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

	public String getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	// end

	// region -- Methods --

	public VoucherDto() {

	}

	// end
}