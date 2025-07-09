package com.example.gestion_academica.controladores;

import com.example.gestion_academica.dto.AlumnoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_academica.servicios.AlumnoServicio;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlumnoControlador {
    private final AlumnoServicio alumnoServicio;

    public AlumnoControlador(AlumnoServicio alumnoServicio) {
        this.alumnoServicio = alumnoServicio;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> obtenerTodos() {
        return ResponseEntity.ok(alumnoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(alumnoServicio.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> crear(@RequestBody AlumnoDTO dto) {
        return ResponseEntity.status(201).body(alumnoServicio.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> actualizar(@PathVariable Long id, @RequestBody AlumnoDTO dto) {
        try {
            return ResponseEntity.ok(alumnoServicio.modificar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alumnoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
