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
@Table(name = "func_role", schema = "public")
public class FuncRole {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_role_id_seq_generator")
	@SequenceGenerator(name = "func_role_id_seq_generator", sequenceName = "func_role_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer idRole;

	@Column(columnDefinition = "integer", name = "id_func")
	private String idFunc;

	@Column(columnDefinition = "boole", name = "is_deleted")
	private boolean isDeleted;

	@Column(columnDefinition = "integer", name = "create_by")
	private Integer createBy;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "create_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createOn;

	@Column(columnDefinition = "integer", name = "modify_by")
	private Integer modifyBy;

	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "modify_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyOn;

	// end

	// region -- Get set --

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(String idFunc) {
		this.idFunc = idFunc;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
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

	public FuncRole() {
	}

	// end
}