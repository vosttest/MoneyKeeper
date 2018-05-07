package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountReq {
	// region -- Fields --

	@JsonProperty(value = "userName")
	private String code;

	@JsonProperty(value = "password")
	private String text;

	@JsonProperty(value = "firstName")
	private String description;

	@JsonProperty(value = "lastName")
	private String sequence;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "contactNo")
	private String contactNo;

	@JsonProperty(value = "remarks")
	private String remarks;

	// end

	// region -- Get set --

	// end

	// region -- Methods --

	public AccountReq() {
	}

	// end
}