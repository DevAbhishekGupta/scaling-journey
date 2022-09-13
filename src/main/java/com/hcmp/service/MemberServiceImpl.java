package com.hcmp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.model.MemberDetails;
import com.hcmp.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ClaimService claimService;

	@Autowired
	private PhysicianService physicianService;

	@Override
	public Member addMember(Member member) {

		if (member != null) {
			return memberRepository.saveAndFlush(member);
		} else {
			return null;
		}

	}

	@Override
	public Member getMemberById(Integer memberId) {

		Member member = memberRepository.findById(memberId).get();

		if (member != null) {
			return member;
		}
		return null;
	}

	@Override
	public List<MemberDetails> getMemberByName(String firstName, String lastName) {
		List<Member> membersList = memberRepository.getMemberByName(firstName, lastName);

		List<MemberDetails> memberDetailsList = new ArrayList<>();
		memberDetailsList = getMembersAllDetails(membersList);
		if (memberDetailsList != null && memberDetailsList.size() > 0) {
			return memberDetailsList;
		} else {
			return null;
		}
	}

	@Override
	public List<MemberDetails> getMemberByPhysician(Integer physicianId) {
		List<Member> membersList = memberRepository.getMemberByPhysician(physicianId);
		List<MemberDetails> memberDetailsList = new ArrayList<>();
		memberDetailsList = getMembersAllDetails(membersList);
		if (memberDetailsList != null && memberDetailsList.size() > 0) {
			return memberDetailsList;
		} else {
			return null;
		}
	}

	/*
	@Override
	public Member getMemberByClaimId(Integer claimId) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public List<Member> getAllMembers() {

		List<Member> allMembersList = memberRepository.findAll();

		if (allMembersList != null && allMembersList.size() > 0) {
			return allMembersList;
		}

		return null;

	}

	@Override
	public List<MemberDetails> getMemberDetailsById(Integer memberId) {

		Member member = memberRepository.findById(memberId).get();
		List<MemberDetails> memberDetailsList = new ArrayList<>();
		memberDetailsList = getMemberDetails(member);
		if (memberDetailsList != null) {
			return memberDetailsList;
		} else {
			return null;
		}

	}

	@Override
	public List<MemberDetails> getMemberDetailsByUserId(Integer userid) {

		Member member = memberRepository.getMemberDetailsByUserId(userid);
		List<MemberDetails> memberDetailsList = new ArrayList<>();
		memberDetailsList = getMemberDetails(member);
		if (memberDetailsList != null) {
			return memberDetailsList;
		} else {
			return null;
		}

	}

	public List<MemberDetails> getMemberDetails(Member member) {

		List<MemberDetails> memberDetailsList = new ArrayList<>();

		MemberDetails memberDetails = new MemberDetails();
		String physicianName;

		if (member != null) {
			physicianName = physicianService.getPhysicianName(member.getFkPhysicianId());

			memberDetails.setMemberId(member.getMemberId());
			memberDetails.setFirstName(member.getFirstName());
			memberDetails.setLastName(member.getLastName());
			memberDetails.setPhysician(physicianName);

			List<Claim> claimList = claimService.getClaimByMemberId(member.getMemberId());

			if (claimList != null && claimList.size() > 0) {

				for (Claim claim : claimList) {
					MemberDetails memberDetail = new MemberDetails();
					memberDetail.setMemberId(member.getMemberId());
					memberDetail.setFirstName(member.getFirstName());
					memberDetail.setLastName(member.getLastName());
					memberDetail.setPhysician(physicianName);
					memberDetail.setClaimId(claim.getClaimId());
					memberDetail.setClaimAmount(claim.getClaimAmount());
					memberDetail.setSubmittedDate(claim.getClaimDate());

					memberDetailsList.add(memberDetail);
				}

				return memberDetailsList;

			} else {
				memberDetailsList.add(memberDetails);
				return memberDetailsList;
			}

		}
		return null;

	}

	public List<MemberDetails> getMembersAllDetails(List<Member> membersList) {

		List<MemberDetails> memberDetailsList = new ArrayList<>();

		String physicianName;

		if (membersList != null && membersList.size() > 0) {
			for (Member member : membersList) {
				MemberDetails memberDetails = new MemberDetails();
				physicianName = physicianService.getPhysicianName(member.getFkPhysicianId());

				memberDetails.setMemberId(member.getMemberId());
				memberDetails.setFirstName(member.getFirstName());
				memberDetails.setLastName(member.getLastName());
				memberDetails.setPhysician(physicianName);

				List<Claim> claimList = claimService.getClaimByMemberId(member.getMemberId());

				if (claimList != null && claimList.size() > 0) {

					for (Claim claim : claimList) {
						MemberDetails memberDetail = new MemberDetails();
						memberDetail.setMemberId(member.getMemberId());
						memberDetail.setFirstName(member.getFirstName());
						memberDetail.setLastName(member.getLastName());
						memberDetail.setPhysician(physicianName);
						memberDetail.setClaimId(claim.getClaimId());
						memberDetail.setClaimAmount(claim.getClaimAmount());
						memberDetail.setSubmittedDate(claim.getClaimDate());

						memberDetailsList.add(memberDetail);
						// return memberDetailsList;
					}

				} else {
					memberDetailsList.add(memberDetails);
					// return memberDetailsList;
				}
			}
			return memberDetailsList;
		}
		return null;
	}

	@Override
	public Member addUserMember(Member member) {

		if (member != null) {
			return memberRepository.saveAndFlush(member);
		} else {
			return null;
		}

	}
	
	@Override
	public List<MemberDetails> getMemberByClaimId(Integer claimId) {
		Claim claim = claimService.getClaimById(claimId);

		if(claim != null) {
			Member member = getMemberById(claim.getFkMemberId());
			
			List<MemberDetails> memberDetailsList = new ArrayList<>();
			
			MemberDetails memberDetails = new MemberDetails();
			
			String physicianName = physicianService.getPhysicianName(member.getFkPhysicianId());
			
			memberDetails.setMemberId(member.getMemberId());
			memberDetails.setFirstName(member.getFirstName());
			memberDetails.setLastName(member.getLastName());
			memberDetails.setPhysician(physicianName);
			memberDetails.setClaimId(claim.getClaimId());
			memberDetails.setClaimAmount(claim.getClaimAmount());
			memberDetails.setSubmittedDate(claim.getSubmittedAt());
			
			memberDetailsList.add(memberDetails);
			
			return memberDetailsList;
		}
		return null;
	}

	@Override
	public Member getMemberByUserId(Integer userId) {
		Member member = memberRepository.getMemberDetailsByUserId(userId);
		if(member !=null) {
			return member;
		}else {
			return null;
		}
		
	}

	@Override
	public Member updateCompany(Member member) {
		Member mem = memberRepository.findById(member.getMemberId()).get();
		if(mem != null) {
			mem.setFirstName(member.getFirstName());
			mem.setLastName(member.getLastName());
			mem.setAddress(member.getAddress());
			mem.setCity(member.getCity());
			mem.setState(member.getState());
			mem.setEmail(member.getEmail());
			mem.setDob(member.getDob());
			mem.setModifiedAt(LocalDate.now());
			
			return memberRepository.saveAndFlush(mem);
			
		}else {
			return null;
		}
	}

}
