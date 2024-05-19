package com.example.Universidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    @Autowired
    private UniversidadRepository universidadRepository;

    @PutMapping("/{requestedId}")
    public ResponseEntity<Object> updateUniversidad(@PathVariable Long requestedId, @RequestBody Universidad universidadUpdate) {
        return universidadRepository.findById(requestedId)
                .map(universidad -> {
                    universidad.setNombre(universidadUpdate.getNombre());
                    universidad.setApellido(universidadUpdate.getApellido());
                    universidad.setCorreo(universidadUpdate.getCorreo());
                    universidad.setNumero(universidadUpdate.getNumero());
                    universidadRepository.save(universidad);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}