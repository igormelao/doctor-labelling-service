package com.doctorlabel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.doctorlabel.controller.dto.CaseDto;
import com.doctorlabel.controller.form.CaseForm;
import com.doctorlabel.controller.form.CloseCaseForm;
import com.doctorlabel.controller.form.CreateLabelCaseForm;
import com.doctorlabel.controller.form.UpdateCaseForm;
import com.doctorlabel.model.Case;
import com.doctorlabel.model.User;
import com.doctorlabel.repository.CaseRepository;
import com.doctorlabel.repository.UserRepository;
import com.doctorlabel.service.LabelProxy;

/**
* 
* <p>This class is a Rest Controller for resources of Cases</p>
* 
* <p>There are 3 dependencies related with this Rest Controller and it is Injectabled by SpringBoot by tag @Autowired <p>
* 
* <ol>
* 	<li>CaseRepository - Responsible to get all resources from Case</li>
*  <li>UserRepository - Responsible to get all resources from User</li>
*  <li>LabelProxy - Responsible to comunicate with a Third Services to get the Labels informations @see(http://localhost:8080/swagger-ui.html#/labels-controller/listAllUsingGET)</li>
* </ol>
* 
* <p>The list available for this Rest AuthenticationController are described below:<p>
* 
*  <ul> 
*  	<li>
*  		Request Mapping: "/cases" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>return a List CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/cases/nextCase" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
*   <li>
*  		Request Mapping: "/cases/{id}" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/cases/label/{labelId}" 
*  		<ul>
*  			<li>METHOD: GET</li>
*  			<li>return a List of ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/cases" 
*  		<ul>
*  			<li>METHOD: POST</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/cases/{id}/label" 
*  		<ul>
*  			<li>METHOD: POST</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* 	<li>
*  		Request Mapping: "/cases/{id}" 
*  		<ul>
*  			<li>METHOD: PUT</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* <li>
*  		Request Mapping: "/cases/{id}/close" 
*  		<ul>
*  			<li>METHOD: PUT</li>
*  			<li>return a ResponseEntity CaseDto ( @see {@link com.doctorlabel.controller.dto.CaseDto})</li>
*  		</ul>
* 	</li>
* <li>
*  		Request Mapping: "/cases//{id}/label/{labelId}" 
*  		<ul>
*  			<li>METHOD: DELETE</li>
*  			<li>return a Empty ResponseEntity)</li>
*  		</ul>
* 	</li>
*  </ul>
*  
*  <p> You can see more details of all resource @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller">http://localhost:8081/swagger-ui.html#/cases-controller</a>
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
*/
@RestController
@RequestMapping("/cases")
public class CasesController {

	/**
	 * <p>This is a Spring Boot interface that it's responsible to manage resources into database for Cases</p>
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private CaseRepository caseRepository;
	/**
	 * <p>This is a Spring Boot interface that it's responsible to manage resources into database for Users</p>
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * <p>This is a Spring Boot interface that it's responsible to manage resources into third service applicaiton for Labels</p>
	 * @author Igor Melão (igormelao@gmail.com)
	 * @since 0.0.1-SNAPSHOT
	 */
	@Autowired
	private LabelProxy labelProxy;

	
	/**
	 * <p>This is a resource to return all Cases</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @return List of CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/listAllUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping
	public List<CaseDto> listAll() {
		List<Case> cases = caseRepository.findAll();
		return CaseDto.convert(cases, labelProxy);
	}
	
	/**
	 * <p>This is a resource to return next Case</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/getNextCaseUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping("/nextCase")
	public ResponseEntity<CaseDto> getNextCase(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Case> doctorCase = caseRepository.getNextCase(user.getId());
		
		if(!doctorCase.isEmpty()) {
			return ResponseEntity.ok(new CaseDto(doctorCase.get(), labelProxy));
		}
		
		return ResponseEntity.notFound().build();
	}

	/**
	 * <p>This is a resource to return one Case by your identification ID</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/findByUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CaseDto> findBy(@PathVariable Long id) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			return ResponseEntity.ok(new CaseDto(optionalCase.get(), labelProxy));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * <p>This is a resource to return all Cases by Label</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/findAllByLabelUsingGET">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@GetMapping("/label/{labelId}")
	public ResponseEntity<List<CaseDto>> findAllByLabel(@PathVariable String labelId) {
		List<Case> cases = caseRepository.findAllByLabel(labelId);

		if (!cases.isEmpty()) {
			return ResponseEntity.ok(CaseDto.convert(cases, labelProxy));
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * <p>This is a resource to create new Case</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/createUsingPOST">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<CaseDto> create(@Valid @RequestBody CaseForm form, UriComponentsBuilder uriBuilder) {
		Case doctorCase = form.convert();
		caseRepository.save(doctorCase);

		URI uri = uriBuilder.path("/cases/{id}").buildAndExpand(doctorCase.getId()).toUri();
		return ResponseEntity.created(uri).body(new CaseDto(doctorCase, labelProxy));
	}

	/**
	 * <p>This is a resource to insert new Label into Case. Meaning that Doctor it's labelling the Case</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/createLabelUsingPOST">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PostMapping("/{id}/label")
	@Transactional
	public ResponseEntity<CaseDto> createLabel(@PathVariable Long id, @Valid @RequestBody CreateLabelCaseForm form,
			UriComponentsBuilder uriBuilder) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = form.insertLabel(id, caseRepository, userRepository);
			return ResponseEntity.ok(new CaseDto(doctorCase, labelProxy));
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * <p>This is a resource to update  electronicHealthRecord of one Case existed.</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/createLabelUsingPOST">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CaseDto> updateCase(@PathVariable Long id, @Valid @RequestBody UpdateCaseForm form) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = form.updateCase(id, caseRepository);
			return ResponseEntity.ok(new CaseDto(doctorCase, labelProxy));
		}

		return ResponseEntity.notFound().build();

	}
	
	/**
	 * <p>This is a resource to close Case existed. That means that the Doctor already put all Labels related with this Case</p>
	 * <p>closing the Case, it;s not more possible to update</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/closeCaseUsingPUT">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@PutMapping("/{id}/close")
	@Transactional
	public ResponseEntity<CaseDto> closeCase(@PathVariable Long id) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = CloseCaseForm.closeCase(id, caseRepository);
			return ResponseEntity.ok(new CaseDto(doctorCase, labelProxy));
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * <p>This is a resource to remove a Label associated inside of Case existed.</p>
	 * 
	 * @author Igor Melão (igormelao@gmail.com)
	 * @param
	 * @return ResponseEntity CaseDto @see(com.doctorlabel.controller.dto.CaseDto)
	 * @see <a href="http://localhost:8081/swagger-ui.html#/cases-controller/removeLabelUsingDELETE">Swagger API Documentation</a>
	 * @since 0.0.1-SNAPSHOT
	 */
	@DeleteMapping("/{id}/label/{labelId}")
	@Transactional
	public ResponseEntity<?> removeLabel(@PathVariable Long id, @PathVariable String labelId) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = optionalCase.get();
			if(!doctorCase.getLabels().isEmpty())
			{
				doctorCase.getLabels().remove(labelId);
				return ResponseEntity.ok().build();
			}
		}

		return ResponseEntity.notFound().build();
	}
}
