package com.tva.mk.req;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tva.mk.common.Const;

public class VoucherSearchReq {
	// region -- Fields --

	/**
	 * Find data in text or description column
	 */
	@JsonProperty(value = "keyword")
	private String keyword;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.DateTime.YMD)
	@JsonProperty(value = "date")
	private Date date;

	// end

	// region -- Get set --

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// end

	// region -- Methods --

	public VoucherSearchReq() {
	}

	public VoucherSearchReq(String keyword, Date date) {
		this.keyword = keyword;
		this.date = date;
	}

	// end
}