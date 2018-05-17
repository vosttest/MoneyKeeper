package com.tva.mk.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "text")
	private String text;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "balance")
	private Float balance;

	@JsonProperty(value = "currency")
	private String currency;

	@JsonProperty(value = "bank")
	private String bank;

	@JsonProperty(value = "startDate")
	private Date startDate;

	@JsonProperty(value = "term")
	private String term;

	@JsonProperty(value = "interestRate")
	private Float interestRate;

	@JsonProperty(value = "interestRateFree")
	private Float interestRateFree;

	@JsonProperty(value = "interestPaid")
	private String interestPaid;

	@JsonProperty(value = "termEnded")
	private String termEnded;

	@JsonProperty(value = "accountId")
	private int accountId;

	@JsonProperty(value = "sequence")
	private int sequence;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	public Float getInterestRateFree() {
		return interestRateFree;
	}

	public void setInterestRateFree(Float interestRateFree) {
		this.interestRateFree = interestRateFree;
	}

	public String getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(String interestPaid) {
		this.interestPaid = interestPaid;
	}

	public String getTermEnded() {
		return termEnded;
	}

	public void setTermEnded(String termEnded) {
		this.termEnded = termEnded;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	// end

	// region -- Methods --

	public AccountReq() {
	}

	// end
}