package com.doctorlabel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.doctorlabel.controller.form.CreateLabelCaseForm;
import com.doctorlabel.controller.form.UpdateCaseForm;
import com.doctorlabel.model.Case;
import com.doctorlabel.repository.CaseRepository;

@RestController
@RequestMapping("/cases")
public class CasesController {

	@Autowired
	private CaseRepository caseRepository;

	@GetMapping
	public List<CaseDto> listAll() {
		List<Case> cases = caseRepository.findAll();
		return CaseDto.convert(cases);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CaseDto> findBy(@PathVariable Long id) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			return ResponseEntity.ok(new CaseDto(optionalCase.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/label/{labelId}")
	public ResponseEntity<List<CaseDto>> findAllByLabel(@PathVariable String labelId) {
		List<Case> cases = caseRepository.findAllByLabel(labelId);

		if (!cases.isEmpty()) {
			return ResponseEntity.ok(CaseDto.convert(cases));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CaseDto> create(@Valid @RequestBody CaseForm form, UriComponentsBuilder uriBuilder) {
		Case doctorCase = form.convert();
		caseRepository.save(doctorCase);

		URI uri = uriBuilder.path("/cases/{id}").buildAndExpand(doctorCase.getId()).toUri();
		return ResponseEntity.created(uri).body(new CaseDto(doctorCase));
	}

	@PostMapping("/{id}/label")
	@Transactional
	public ResponseEntity<CaseDto> createLabel(@PathVariable Long id, @Valid @RequestBody CreateLabelCaseForm form,
			UriComponentsBuilder uriBuilder) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = form.insertLabel(id, form, caseRepository);
			return ResponseEntity.ok(new CaseDto(doctorCase));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CaseDto> updateCase(@PathVariable Long id, @Valid @RequestBody UpdateCaseForm form) {
		Optional<Case> optionalCase = caseRepository.findById(id);
		if (optionalCase.isPresent()) {
			Case doctorCase = form.updateCase(id, form, caseRepository);
			return ResponseEntity.ok(new CaseDto(doctorCase));
		}

		return ResponseEntity.notFound().build();

	}

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
