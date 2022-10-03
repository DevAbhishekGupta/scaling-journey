package com.hcmp.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmp.model.Member;
import com.hcmp.model.User;
import com.hcmp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PhysicianService physicianService;
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public User addUser(User user) {
		if(user!=null)
		{
			User user1 =  userRepo.saveAndFlush(user);
			
			if(user1!=null && user1.getRole().equals('M')) {
				Member member = new Member();
				//member.setUserName(member.getUserName());
				//member.setPassWord(member.getPassWord());
				member.setFkPhysicianId(physicianService.getRandomPhysician());
				member.setFkUserId(user1.getUserId());
				member.setCreatedAt(LocalDate.now());
				
				memberService.addMember(member);
			}
			
			return user1;
			
		}
		else {
			return null;
		}
			
	}
	
	@Override
	public boolean validateUserLogin(String username, String password) {
		User user = userRepo.validateUser(username, password);
		
		if(user!=null)
		{
			return true;
		}
		
		else {
			return false;
		}
	}

	@Override
	public User getUserDetails(String username) {
		
		return userRepo.getUserDetails(username);
	}
	
	
	@Override
	public User addMemberUser(User user) {
		if(user!=null)
		{
			return userRepo.saveAndFlush(user);
			
		}
		else {
			return null;
		}
			
	}
	
	/*
	@Override
	public String isUserExist(String userName) {
		String username = userRepo.isUserExist(userName);
		
		if(username != null) {
			return username;
		}else {
			return null;
		}
	}
	*/
}
