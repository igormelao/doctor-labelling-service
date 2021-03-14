package com.doctorlabel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doctorlabel.model.Case;

/**
 * 
 * Interface responsible to manage database storage for Case.
 * 
 * @author Igor Melão (igormelao@gmail.com)
 * @Date:  14-03-2021
 * @since  0.0.1-SNAPSHOT
 */
public interface CaseRepository extends JpaRepository<Case, Long> {

	/**
	 * <p>Find Case through by Label</p>
	 * <p> This is a native query that bring to us the All Cases that contains the Label associated</p>
	 * <p> This not retrieve all informations for Label, it's only get the Label Id that was storage before</p>
	 * <p> To get all informations of one Label, you will need to use LabelProxy.java. </p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @see com.doctorlabel.service.LabelProxy
	 * @param
	 * @return List< Case > @see com.doctorlabel.model.Case
	 * @since 0.0.1-SNAPSHOT
	 */
	@Query(value = "SELECT * FROM cases c inner join cases_label cl ON c.id = cl.case_id where cl.label_id = :labelId", nativeQuery = true)
	List<Case> findAllByLabel(@Param("labelId") String labelId);

	
	/**
	 * <p>Find next Case or the actual Case that Doctor are working</p>
	 * <p>This is a native query that bring to us the actual Case that Doctor are working or if not working</p>
	 * <p>bring the next case that it's not associated with no one and the state it's not equals to CLOSE.</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return Optional < Case > 
	 * @see com.doctorlabel.model.Case
	 * @since 0.0.1-SNAPSHOT
	 */
	@Query(value = "SELECT * FROM cases c WHERE (c.doctor_id = :doctorId AND c.state != 'CLOSE') OR (c.doctor_id is null && c.state != 'CLOSED') ORDER BY c.date_create ASC LIMIT 1", nativeQuery = true)
	Optional<Case> getNextCase(@Param("doctorId")Long doctorId);

}
