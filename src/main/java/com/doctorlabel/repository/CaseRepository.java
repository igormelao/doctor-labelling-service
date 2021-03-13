package com.doctorlabel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doctorlabel.model.Case;

public interface CaseRepository extends JpaRepository<Case, Long> {

	@Query(value = "SELECT * FROM cases c inner join cases_label cl ON c.id = cl.case_id where cl.label_id = :labelId", nativeQuery = true)
	List<Case> findAllByLabel(@Param("labelId") String labelId);

	@Query(value = "SELECT * FROM cases c WHERE (c.doctor_id = :doctorId AND c.state != 'CLOSE') OR (c.doctor_id is null && c.state != 'CLOSED') ORDER BY c.date_create ASC LIMIT 1", nativeQuery = true)
	Optional<Case> getNextCase(@Param("doctorId")Long doctorId);

}
