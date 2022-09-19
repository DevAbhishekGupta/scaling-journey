package com.hcmp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcmp.model.User;
import com.hcmp.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin("*")
@RestController
@RequestMapping("auth/api")
public class AuthController {
	
	private Map<String,String> mObj = new HashMap<String,String>();
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		
		ResponseEntity<?> responseEntity = null;
		try {
			String jwtToken = generateToken(user.getUsername(), user.getPassword());
			mObj.put("message", "User successfully logged in");
			mObj.put("token", jwtToken);
			
			if(jwtToken != null && !jwtToken.trim().isEmpty()) {
				User user1 = userServiceImpl.getUserDetails(user.getUsername());
				mObj.put("role", user1.getRole().toString());
				mObj.put("userid", user1.getUserId().toString());
				responseEntity = new ResponseEntity<>(mObj, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			mObj.put("message", "User unsuccessful to be logged in");
			//mObj.put("token", null);
			responseEntity = new ResponseEntity<>(mObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
		
	}

	public String generateToken(String username, String password) throws ServletException, Exception {
		String jwtToken = "";
		if (username == null || password == null) {
			throw new ServletException("Please send valid username and password");
		}
		boolean flag = userServiceImpl.validateUserLogin(username, password);

		if (!flag) {
			throw new ServletException("Invalid credentials");
		} else {
			jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 3600000))
					.signWith(SignatureAlgorithm.HS256, "my secret sign").compact();

		}

		return jwtToken;
	}

}
