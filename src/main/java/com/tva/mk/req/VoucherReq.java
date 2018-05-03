package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherReq {
	// region -- Fields --

	@JsonProperty(value = "create_by")
	private Integer createBy;

	@JsonProperty(value = "type")
	private String type;

	// end

	// region -- Get set --

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// end

	// region -- Methods --

	public VoucherReq() {
	}

	// end
}