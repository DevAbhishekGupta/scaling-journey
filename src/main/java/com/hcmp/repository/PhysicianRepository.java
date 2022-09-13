package com.hcmp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcmp.model.Physician;

@Repository
@Transactional
public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

	@Query(nativeQuery = true, value = "select ph.physician_id from (select p.* from physician p order by rand() limit 7,1) as ph")
	public Integer getRandomPhysician();
	
	@Query(value = "SELECT p.physicianName from Physician p where p.physicianId = :physicianId ")
	public String getPhysicianName(@Param("physicianId") Integer physicianId);
	
}
