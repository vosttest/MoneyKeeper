package com.tva.mk.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherDetailReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "id_master")
	private Integer idMaster;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(Integer idMaster) {
		this.idMaster = idMaster;
	}

	// end

	// region -- Methods --

	public VoucherDetailReq() {
	}

	// end
}