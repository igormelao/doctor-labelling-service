package com.doctorlabel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doctorlabel.model.Case;

public interface CaseRepository extends JpaRepository<Case, Long> {

	@Query(value = "SELECT * FROM cases c inner join cases_label cl ON c.id = cl.case_id where cl.label_id = :labelId", nativeQuery = true)
	List<Case> findAllByLabel(@Param("labelId") String labelId);

	@Query(value = "SELECT c FROM Case c ORDER BY c.dateCreate")
	Page<Case> getNextCase(PageRequest orderByDateCreate);

}
