package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "value")
	private String value;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "isFirst")
	private boolean isFirst;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	// end

	// region -- Methods --

	public SettingReq() {
	}

	// end
}