package com.tva.mk.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class VoucherDetailDto {
	// region -- Fields --

	@Column(columnDefinition = "integer", name = "id")
	private Integer id;

	@Column(columnDefinition = "integer", name = "id_master")
	private Integer idMaster;

	@Column(columnDefinition = "varchar(100)", name = "value")
	private String value;

	@Column(columnDefinition = "float", name = "name")
	private Float amount;

	@Column(columnDefinition = "bool", name = "is_deleted")
	private Boolean isDeleted;

	@Column(columnDefinition = "integer", name = "create_by")
	private Integer createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "create_on")
	private Date createOn;

	@Column(columnDefinition = "integer", name = "modify_by")
	private Integer modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "modify_on")
	private Date modifyOn;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}

	// end

	// region -- Methods --

	public VoucherDetailDto() {
	}

	// end
}