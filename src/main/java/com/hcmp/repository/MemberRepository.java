package com.hcmp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcmp.model.Member;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query(value = "SELECT m from Member m where m.firstName =:firstName and m.lastName =:lastName ")
	public List<Member> getMemberByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query(value = "SELECT m from Member m where m.fkPhysicianId = :physicianId")
	public List<Member> getMemberByPhysician(@Param("physicianId") Integer physicianId);
	
	//@Query(nativeQuery = true, value = "SELECT m from Member m where m.userame = :username")
	@Query(value = "SELECT m from Member m where m.fkUserId = :userid")
	public Member getMemberDetailsByUserId(@Param("userid") Integer userid);
	
}
