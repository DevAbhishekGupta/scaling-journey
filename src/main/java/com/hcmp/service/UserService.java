package com.hcmp.service;

import com.hcmp.model.User;

public interface UserService {
	
	public User addUser(User user);

	public boolean validateUserLogin(String username, String password);
	
	public User getUserDetails(String username);
	
	public User addMemberUser(User user);
	
	//public String isUserExist(String userName);

}
