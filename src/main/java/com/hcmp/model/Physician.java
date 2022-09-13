package com.hcmp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Physician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer physicianId;
	private String physicianName;
	private String physicianState;
	public Integer getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getPhysicianState() {
		return physicianState;
	}
	public void setPhysicianState(String physicianState) {
		this.physicianState = physicianState;
	}
	

}
