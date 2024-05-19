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
    private ResponseEntity<Void> createUniversidad(@RequestBody Universidad newUniversidadRequest, UriComponentsBuilder ucb) {
        Universidad savedUniversidad = universidadRepository.save(newUniversidadRequest);
        URI locationOfNewUniversidad = ucb.path("/universidades/{id}").buildAndExpand(savedUniversidad.id()).toUri();
        return ResponseEntity.created(locationOfNewUniversidad).build();
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

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateUniversidad(@PathVariable Long requestedId, @RequestBody Universidad universidadUpdate) {
        Optional<Universidad> optional = universidadRepository.findById(requestedId);
        if (optional.isPresent()) {
            Universidad universidad = optional.get();
            Universidad updatedUniversidad = new Universidad(
                universidad.id(), 
                universidadUpdate.nombre(), 
                universidadUpdate.apellido(), 
                universidadUpdate.correo(), 
                universidadUpdate.numero()
            );
            universidadRepository.save(updatedUniversidad);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
