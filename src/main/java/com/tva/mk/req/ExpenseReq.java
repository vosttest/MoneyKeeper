package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "parentId")
	private int parentId;

	@JsonProperty(value = "isDelete")
	private Boolean isDelete;

	@JsonProperty(value = "icon")
	private String icon;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	// end

	// region -- Methods --

	public ExpenseReq() {
	}

	// end
}