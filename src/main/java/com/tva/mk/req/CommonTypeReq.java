package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonTypeReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "typeName")
	private String typeName;

	@JsonProperty(value = "isDeleted")
	private Boolean isDeleted;

	// end

	// region -- Get set --
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// end

	// region -- Methods --

	public CommonTypeReq() {
	}

	// end
}