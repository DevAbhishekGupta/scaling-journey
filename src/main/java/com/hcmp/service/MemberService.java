package com.hcmp.service;

import java.util.List;

import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;

public interface MemberService {
	
	public Member addMember(Member member);
	public Member getMemberById(Integer memberId);
	public List<MemberDetails> getMemberByName(String firstName, String lastName);
	public List<MemberDetails> getMemberByPhysician(Integer physicianId);
	//public Member getMemberByClaimId(Integer claimId);
	
	public List<Member> getAllMembers();
	
	public List<MemberDetails> getMemberDetailsById(Integer memberId);
	public List<MemberDetails> getMemberDetailsByUserId(Integer userid);
	
	public Member addUserMember(Member member);
	
	public List<MemberDetails> getMemberByClaimId(Integer claimId);
	
	public Member getMemberByUserId(Integer userId);
	
	public Member updateCompany(Member member);

}
