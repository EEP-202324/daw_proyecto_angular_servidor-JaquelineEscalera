package com.example.universidades;

import java.net.URI;
import java.util.Optional;

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

@RestController
@RequestMapping("/universidades")
public class UniversidadController {
	private final UniversidadRepository universidadRepository;

	private UniversidadController(UniversidadRepository universidadRepository) {
		this.universidadRepository = universidadRepository;
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
	private ResponseEntity<Void> createUniversidad(@RequestBody  Universidad newUniversidadRequest, UriComponentsBuilder ucb) {
	   Universidad savedUniversidad =  universidadRepository.save(newUniversidadRequest);
	   URI locationOfNewUniversidad = ucb
	            .path("universidades/{id}")
	            .buildAndExpand(savedUniversidad.getId())
	            .toUri();
	   return ResponseEntity.created(locationOfNewUniversidad).build();
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Universidad>> findAll() {
	   return ResponseEntity.ok(universidadRepository.findAll());
	}
	
	@PutMapping("/{requestedId}")
    private ResponseEntity<Void> putUniversidad(@PathVariable Long requestedId, @RequestBody Universidad universidadActualizada) {
        Optional<Universidad> optional = universidadRepository.findById(requestedId);
        if (optional.isPresent()) {
            Universidad universidad = optional.get();
            Universidad updateUniversidad = new Universidad (
                        universidad.getId(),
                        universidadActualizada.getNombre(),
                        universidadActualizada.getGmail(),
                        universidadActualizada.getNumero());
            universidadRepository.save(updateUniversidad);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteUniversidad(@PathVariable Long id) {
		  universidadRepository.deleteById(id); // Add this line
		return ResponseEntity.noContent().build();
	}
}