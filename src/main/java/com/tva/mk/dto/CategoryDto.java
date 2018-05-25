package com.tva.mk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "parentId")
	private Integer parentId;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "count")
	private Integer count;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	// end

	// region -- Methods --

	public CategoryDto() {

	}

	// end
}