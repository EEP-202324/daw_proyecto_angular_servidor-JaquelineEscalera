package com.example.Universidad;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {
	
	private final UniversidadRepository universidadRepository;
	
	private UniversidadController(UniversidadRepository universidadRepository) {
		this.universidadRepository = universidadRepository;
	}
	
	@GetMapping
	private ResponseEntity<List<Universidad>> findAll(Pageable pageable) {
	    Page<Universidad> page = universidadRepository.findAll(
	            PageRequest.of(
	                    pageable.getPageNumber(),
	                    pageable.getPageSize(),
	                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
	    ));
	    return ResponseEntity.ok(page.getContent());
	}
	
	@GetMapping("/{requestedId}")
	private ResponseEntity<Universidad> findById(@PathVariable Long requestedId) {
		Optional<Universidad> universidadOptional = universidadRepository.findById(requestedId);
		if (universidadOptional.isPresent()) {
			return ResponseEntity.ok(universidadOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	private ResponseEntity<Void> createUniversidad(@RequestBody Universidad nuevaUniversidad,
			UriComponentsBuilder ucb) {
		Universidad universidadGuardada = universidadRepository.save(nuevaUniversidad);
		URI locationOfNewUniversidad = ucb.path("universidades/{id}").buildAndExpand(universidadGuardada.id()).toUri();
		return ResponseEntity.created(locationOfNewUniversidad).build();
	}
}