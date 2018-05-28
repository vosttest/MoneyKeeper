package com.tva.mk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tva.mk.common.Const;

@Entity
@Table(name = "setting", schema = "public")
@SqlResultSetMapping(name = "updateResult", columns = { @ColumnResult(name = "count") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "updateSetting", query = "UPDATE setting SET status = 'INA', value = NULL WHERE code = 'SET003' and user_id = ?", resultSetMapping = "updateResult") })
public class Setting {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setting_id_seq_generator")
	@SequenceGenerator(name = "setting_id_seq_generator", sequenceName = "public.setting_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(64)")
	private String code;

	@Column(columnDefinition = "varchar(64)")
	private String value;

	@Column(columnDefinition = "varchar(128)")
	private String text;

	@Column(columnDefinition = "varchar(256)")
	private String description;

	@Column(columnDefinition = "char(3)")
	private String status;

	@Column(columnDefinition = "integer")
	private Integer userId;

	@Column(columnDefinition = "integer")
	private Integer createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date createOn;

	@Column(columnDefinition = "integer")
	private Integer modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date modifyOn;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Setting() {
		this.status = Const.STATUS_INACTIVE;
	}

	// end
}