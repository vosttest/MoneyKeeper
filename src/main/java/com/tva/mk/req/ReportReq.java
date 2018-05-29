package com.tva.mk.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportReq {
	// region -- Fields --

	@JsonProperty(value = "accountId")
	private int accountId;

	@JsonProperty(value = "fromDate")
	private Date fromDate;

	@JsonProperty(value = "toDate")
	private Date toDate;

	// end

	// region -- Get set --

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	// end

	// region -- Methods --

	public ReportReq() {
	}

	// end
}