package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportReq {
	// region -- Fields --

	@JsonProperty(value = "accountId")
	private int[] accountId;

	// end

	// region -- Get set --

	public int[] getAccountId() {
		return accountId;
	}

	public void setAccountId(int[] accountId) {
		this.accountId = accountId;
	}

	// end

	// region -- Methods --

	public ReportReq() {
	}

	// end
}