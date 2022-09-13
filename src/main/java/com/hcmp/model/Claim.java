package com.hcmp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Claim {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claimId;
	
	private String claimType;
	private Integer claimAmount;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private LocalDate claimDate;
	private String remarks;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private LocalDate submittedAt;
	
	private Integer fkMemberId;
	
	
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public Integer getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Integer claimAmount) {
		this.claimAmount = claimAmount;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getFkMemberId() {
		return fkMemberId;
	}
	public void setFkMemberId(Integer fkMemberId) {
		this.fkMemberId = fkMemberId;
	}
	public LocalDate getSubmittedAt() {
		return submittedAt;
	}
	public void setSubmittedAt(LocalDate submittedAt) {
		this.submittedAt = submittedAt;
	}	
	
}
