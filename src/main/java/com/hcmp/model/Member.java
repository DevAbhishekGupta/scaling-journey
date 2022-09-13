package com.hcmp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;
	
	private String firstName;
	private String lastName;
	
	@Transient
	private String userName;
	@Transient
	private String passWord;
	
	
	private String address;
	private String state;
	private String city;
	
	@Column(unique = true)
	private String email;
	
	@Column(columnDefinition="DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private LocalDate dob;
	
	
	//@ManyToOne(targetEntity = Physician.class, cascade = CascadeType.ALL)
	private Integer fkPhysicianId;
	
	private Integer fkClaimId;
	
	private Integer fkUserId;
	
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
		
	public Integer getFkPhysicianId() {
		return fkPhysicianId;
	}
	public void setFkPhysicianId(Integer fkPhysicianId) {
		this.fkPhysicianId = fkPhysicianId;
	}
	
	
	
	public Integer getFkClaimId() {
		return fkClaimId;
	}
	public void setFkClaimId(Integer fkClaimId) {
		this.fkClaimId = fkClaimId;
	}
	
		
	public Integer getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(Integer fkUserId) {
		this.fkUserId = fkUserId;
	}
	
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", passWord=" + passWord + ", address=" + address + ", state=" + state + ", city=" + city
				+ ", email=" + email + ", dob=" + dob + ", fkPhysicianId=" + fkPhysicianId + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", getMemberId()=" + getMemberId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getUserName()=" + getUserName()
				+ ", getPassWord()=" + getPassWord() + ", getAddress()=" + getAddress() + ", getState()=" + getState()
				+ ", getCity()=" + getCity() + ", getEmail()=" + getEmail() + ", getDob()=" + getDob()
				+ ", getFkPhysicianId()=" + getFkPhysicianId() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getModifiedAt()=" + getModifiedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
