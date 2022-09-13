package com.hcmp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;
import com.hcmp.model.User;
import com.hcmp.service.ClaimService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("claim/api")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@PostMapping("/addClaim")
	public ResponseEntity<?> addClaim(@RequestBody Claim claim){
		claim.setSubmittedAt(LocalDate.now());
		if(claimService.addClaim(claim)!=null) {
			return new ResponseEntity<Claim>(claim, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Claim not created", HttpStatus.METHOD_FAILURE);
		
	}
	
	/*
	@GetMapping("/getMemberByClaimId/{claimId}")
	public ResponseEntity<?> getMemberByClaimId(@PathVariable("claimId") Integer claimId){
		
		if(claimId !=null && claimId > 0) {
			List<MemberDetails> memberDetails = claimService.getMemberByClaimId(claimId);
			
			if(memberDetails != null && memberDetails.size() > 0) {
				return new ResponseEntity<List<MemberDetails>>(memberDetails, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Member not found", HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<String>("Member Id can't be null", HttpStatus.NO_CONTENT);
		}
	}
	*/
	
}
