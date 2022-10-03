package com.hcmp;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;
import com.hcmp.model.User;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HcmpApplicationTests {

	final static String LOCALHOST = "http://localhost:";
	
	@LocalServerPort
    int randomServerPort;
	
	String token= null;
	
	
	@Test
	public void testAddUserSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/user/api/registerUser";
		URI uri = new URI(baseUrl);
		
		User user = new User();
		user.setUsername("springboot");
		user.setPassword("Password@12");
		user.setRole('M');
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(user,headers),User.class);
        
        
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	
	@Test
	public void testLoginUserSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/auth/api/loginUser";
		URI uri = new URI(baseUrl);
		
		//ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		
		Map<String,String> userMap = new HashMap<>();
		userMap.put("username", "admin");
		userMap.put("password", "Password@12");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ParameterizedTypeReference<Map<String, String>> paramMapTypeRef = new ParameterizedTypeReference<Map<String, String>>(){};
		
		ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(userMap,headers),paramMapTypeRef);
        
        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals(true, responseEntity.getBody().containsKey("token"));
        
        token = responseEntity.getBody().get("token");
		
	}
	
	@Test
	public void testLoginUserFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/auth/api/loginUser";
		URI uri = new URI(baseUrl);
		
		//ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		
		Map<String,String> userMap = new HashMap<>();
		userMap.put("username", "newadmin");
		userMap.put("password", "Password@12");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ParameterizedTypeReference<Map<String, String>> paramMapTypeRef = new ParameterizedTypeReference<Map<String, String>>(){};
		ResponseEntity<Map<String, String>> responseEntity = null;
		try {
			restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(userMap,headers),paramMapTypeRef);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
		
        
        
        //Assert.assertEquals(500, responseEntity.getStatusCodeValue());
        //Assert.assertEquals(false, responseEntity.getBody().containsKey("token"));
		
	}
	
	
	@Test
	public void testAddMemberSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/addMember";
		URI uri = new URI(baseUrl);
		
		Member member = new Member();
		member.setFirstName("testuser");
		member.setLastName("boot");
		member.setUserName("testuser");
		member.setPassWord("Password@12");
		member.setAddress("DLF Cyber City");
		member.setState("Telangana");
		member.setCity("Gachibowli");
		member.setEmail("testuser@gmail.com");
		member.setDob(LocalDate.of(2000, 01, 26));
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(member,headers),Member.class);
        
        
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberDetailsByIdSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberDetailsById/{memberId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("memberId", "11");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberDetailsByIdFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberDetailsById/{memberId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("memberId", "110");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		try {
			ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	@Test
	public void testGetMemberDetailsByUserIdSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberDetailsByUserId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "16");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberDetailsByUserIdFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberDetailsByUserId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "19");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		try {
			ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	//
	
	@Test
	public void testGetMemberIdSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "16");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),Integer.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberIdFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "19");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		try {
			ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),Integer.class);
			Assert.assertEquals(404, responseEntity.getStatusCodeValue());
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	//getMemberByUserId
	
	@Test
	public void testGetMemberByUserIdSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByUserId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "16");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),Member.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberByUserIdFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByUserId/{userid}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("userid", "19");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		try {
			ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),Member.class);
			Assert.assertEquals(404, responseEntity.getStatusCodeValue());
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	
	
	//========================================================================================
	
	@Test
	public void testGetMemberByPhysicianSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByPhysician/{physicianId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("physicianId", "3");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberByPhysicianFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByPhysician/{physicianId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("physicianId", "9");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
        
        try {
        	ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	//===============================================================================================
	
	
	@Test
	public void testGetMemberByClaimIdSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByClaimId/{claimId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("claimId", "14");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testGetMemberByClaimIdFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByClaimId/{claimId}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("claimId", "13");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
        
        try {
        	ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	//===============================================================================================
	
	@Test
	public void testGetMemberByNameSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByName/{firstName}/{lastName}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("firstName", "abhishek1");
		uriVarMap.put("lastName", "gupta");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
		
		        
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testgetMemberByNameFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/mem/api/getMemberByName/{firstName}/{lastName}";
		//URI uri = new URI(baseUrl);
		
		Map<String,Object> uriVarMap = new HashMap<>();
		uriVarMap.put("firstName", "unknown");
		uriVarMap.put("lastName", "person");
		
		URI uri = UriComponentsBuilder.fromUriString(baseUrl)
				.buildAndExpand(uriVarMap).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
        
        try {
        	ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),List.class);
			Assert.fail();
		}catch(HttpServerErrorException e) {
			Assert.assertEquals(500, e.getRawStatusCode());
		}
	}
	
	//=========================================================================================
	
	@Test
	public void testAddClaimSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = LOCALHOST+randomServerPort+"/claim/api/addClaim";
		URI uri = new URI(baseUrl);
		
		Claim claim = new Claim();
		
		claim.setClaimType("Medical");
		claim.setClaimAmount(25121);
		claim.setRemarks("medical urgency");
		claim.setFkMemberId(11);
		claim.setClaimDate(LocalDate.now());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(claim,headers),Claim.class);
        
        
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	
	/*
	@Test
	void contextLoads() {
	}
	*/

}
