package com.tva.mk.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "user", schema = "public")
public class Users {
	// region -- Fields --

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
	@SequenceGenerator(name = "user_id_seq_generator", sequenceName = "public.user_id_seq", allocationSize = 1)
	@Column(columnDefinition = "SERIAL")
	private Integer id;

	@Column(columnDefinition = "varchar(64)")
	private String userName;

	@Column(columnDefinition = "varchar(128)")
	private String email;

	@Column(columnDefinition = "varchar(64)")
	private String accountNo;

	@Column(columnDefinition = "varchar(32)")
	private String firstName;

	@Column(columnDefinition = "varchar(32)")
	private String lastName;

	@Column(columnDefinition = "varchar(16)")
	private String contactNo;

	@Column(columnDefinition = "varchar(128)")
	private String remarks;

	@Column(columnDefinition = "char(3)")
	private String status;

	@Type(type = "pg-uuid")
	private UUID uuid;

	@Type(type = "pg-uuid")
	private UUID eoth;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date eothExpiryOn;

	@Column(columnDefinition = "bool")
	private Boolean isEmailVerified;

	@Column(columnDefinition = "varchar(256)")
	private String passwordHash;

	@Column(columnDefinition = "varchar(256)")
	private String passwordSalt;

	@Column(columnDefinition = "varchar(256)")
	private String poth;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date pothExpiryOn;

	@Column(columnDefinition = "varchar(256)")
	private String passReminderToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date passReminderExpire;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date lockExpiryOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date lastDeclarationOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date lastLoginOn;

	@Column(columnDefinition = "integer")
	private Integer failedAuthAttempts;

	@Column(columnDefinition = "bool")
	private Boolean isLocked;

	@Column(columnDefinition = "bool")
	private Boolean isDeleted;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getEoth() {
		return eoth;
	}

	public void setEoth(UUID eoth) {
		this.eoth = eoth;
	}

	public Date getEothExpiryOn() {
		return eothExpiryOn;
	}

	public void setEothExpiryOn(Date eothExpiryOn) {
		this.eothExpiryOn = eothExpiryOn;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPoth() {
		return poth;
	}

	public void setPoth(String poth) {
		this.poth = poth;
	}

	public Date getPothExpiryOn() {
		return pothExpiryOn;
	}

	public void setPothExpiryOn(Date pothExpiryOn) {
		this.pothExpiryOn = pothExpiryOn;
	}

	public String getPassReminderToken() {
		return passReminderToken;
	}

	public void setPassReminderToken(String passReminderToken) {
		this.passReminderToken = passReminderToken;
	}

	public Date getPassReminderExpire() {
		return passReminderExpire;
	}

	public void setPassReminderExpire(Date passReminderExpire) {
		this.passReminderExpire = passReminderExpire;
	}

	public Date getLockExpiryOn() {
		return lockExpiryOn;
	}

	public void setLockExpiryOn(Date lockExpiryOn) {
		this.lockExpiryOn = lockExpiryOn;
	}

	public Date getLastDeclarationOn() {
		return lastDeclarationOn;
	}

	public void setLastDeclarationOn(Date lastDeclarationOn) {
		this.lastDeclarationOn = lastDeclarationOn;
	}

	public Date getLastLoginOn() {
		return lastLoginOn;
	}

	public void setLastLoginOn(Date lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}

	public Integer getFailedAuthAttempts() {
		return failedAuthAttempts;
	}

	public void setFailedAuthAttempts(Integer failedAuthAttempts) {
		this.failedAuthAttempts = failedAuthAttempts;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsDeleted() {
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

	public Users() {

	}

	// end
}