package com.hcmp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.repository.PhysicianRepository;

@Service
public class PhysicianServiceImpl implements PhysicianService {

	@Autowired
	private PhysicianRepository physicianRepository;
	
	@Override
	public Integer getRandomPhysician() {
		return physicianRepository.getRandomPhysician();
	}

	@Override
	public String getPhysicianName(Integer physicianId) {
		return physicianRepository.getPhysicianName(physicianId);
	}

}
