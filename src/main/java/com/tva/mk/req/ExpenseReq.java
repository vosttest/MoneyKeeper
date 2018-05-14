package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseReq {

	// region -- Fields --
	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "parentId")
	private int parentId;

	// end

	// region -- Get set --

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	// end

	// region -- Methods --

	public ExpenseReq() {
	}

	// end

}
