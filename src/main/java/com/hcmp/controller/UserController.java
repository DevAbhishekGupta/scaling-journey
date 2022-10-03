package com.hcmp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

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

import com.hcmp.model.Member;
import com.hcmp.model.User;
import com.hcmp.service.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("user/api")
public class UserController {

	private Map<String, String> mObj = new HashMap<String, String>();

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/registerUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		if (userServiceImpl.addUser(user) != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("user not added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	@GetMapping("/isUserExist/{userName}")
	public ResponseEntity<?> isUserExist(@PathVariable("userName") String userName) {

		if (userName != null) {
			String username = userServiceImpl.isUserExist(userName);
			boolean isUserExist;
			if (username != null) {
				isUserExist = true;
			} else {
				isUserExist = false;
			}
			return new ResponseEntity<Boolean>(isUserExist, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Username can't be null", HttpStatus.NO_CONTENT);
		}
	}
	*/

}
