package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateReq {
	// region -- Fields --

	@JsonProperty(value = "value")
	private String value;

	@JsonProperty(value = "rate")
	private String rate;

	@JsonProperty(value = "name")
	private String name;

	// end

	// region -- Get set --

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// end

	// region -- Methods --

	public ExchangeRateReq() {
	}

	// end
}