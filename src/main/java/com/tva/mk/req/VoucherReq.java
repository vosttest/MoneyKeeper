package com.tva.mk.req;

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

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "object")
	private String object;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	// end

	// region -- Methods --

	public VoucherReq() {
	}

	// end
}