package com.tva.mk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "voucher_detail", schema = "public")
public class VoucherDetail {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_detail_id_seq_generator")
	@SequenceGenerator(name = "voucher_detail_id_seq_generator", sequenceName = "public.voucher_detail_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "integer", name = "id_master")
	private Integer idMaster;

	@Column(columnDefinition = "integer", name = "id_code")
	private Integer idCode;

	@Column(columnDefinition = "float", name = "amount")
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

	public Integer getIdCode() {
		return idCode;
	}

	public void setIdCode(Integer idCode) {
		this.idCode = idCode;
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

	public VoucherDetail() {
	}

	// end
}