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
@Table(name = "token_authentication", schema = "public")
public class TokenAuthentication {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_authentication_seq_generator")
	@SequenceGenerator(name = "token_authentication_id_seq_generator", sequenceName = "public.token_authentication_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(8)")
	private String clientKey;

	@Column(columnDefinition = "varchar(32)")
	private String module;

	@Column(columnDefinition = "varchar(8)")
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date tokenExpireOn;

	@Column(columnDefinition = "integer")
	private Integer userId;

	@Column(columnDefinition = "bool")
	private boolean isVerified;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpireOn() {
		return tokenExpireOn;
	}

	public void setTokenExpireOn(Date tokenExpireOn) {
		this.tokenExpireOn = tokenExpireOn;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	// end

	// region -- Methods --

	public TokenAuthentication() {
	}

	// end
}