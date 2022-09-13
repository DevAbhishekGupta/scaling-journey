package com.hcmp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcmp.model.Claim;
import com.hcmp.model.Member;

@Repository
@Transactional
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	
	@Query(value = "SELECT c FROM Claim c WHERE c.fkMemberId = :memberId ")
	public List<Claim> getClaimByMemberId(@Param("memberId") Integer memberId);

}
