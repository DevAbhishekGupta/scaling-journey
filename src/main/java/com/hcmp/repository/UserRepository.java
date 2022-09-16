package com.hcmp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcmp.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(@Param("username") String username,@Param("password") String password);
	
	@Query(value="select u from User u where u.username= :username")
	public User getUserDetails(@Param("username") String username);
		
	@Query(value = "SELECT u.username from User u where u.username = :userName")
	public String isUserExist(@Param("userName") String userName);

}
