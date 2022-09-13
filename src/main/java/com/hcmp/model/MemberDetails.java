package com.hcmp.model;

import java.time.LocalDate;

public class MemberDetails {
	
	private Integer memberId;
	private String firstName;
	private String lastName;
	private String physician;
	private Integer claimId;
	private Integer claimAmount;
	private LocalDate submittedDate;
	
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
	public String getPhysician() {
		return physician;
	}
	public void setPhysician(String physician) {
		this.physician = physician;
	}
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public Integer getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Integer claimAmount) {
		this.claimAmount = claimAmount;
	}
	public LocalDate getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}
	
	

}
