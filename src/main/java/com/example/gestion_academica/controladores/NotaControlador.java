package com.example.gestion_academica.controladores;


import com.example.gestion_academica.dto.NotaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_academica.servicios.NotaServicio;

import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotaControlador {
    private final NotaServicio notaServicio;

    public NotaControlador(NotaServicio notaServicio) {
        this.notaServicio = notaServicio;
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> obtenerTodas() {
        return ResponseEntity.ok(notaServicio.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaServicio.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nota usando IDs separados
    @PostMapping
    public ResponseEntity<NotaDTO> crear(
            @RequestParam Long alumnoId,
            @RequestParam Long asignaturaId,
            @RequestParam Double calificacion
    ) {
        NotaDTO nuevaNota = notaServicio.crear(alumnoId, asignaturaId, calificacion);
        return ResponseEntity.status(201).body(nuevaNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> actualizar(
            @PathVariable Long id,
            @RequestParam Double nuevaCalificacion
    ) {
        try {
            return ResponseEntity.ok(notaServicio.modificar(id, nuevaCalificacion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
