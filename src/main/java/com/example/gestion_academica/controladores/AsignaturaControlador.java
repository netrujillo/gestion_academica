package com.example.gestion_academica.controladores;

import com.example.gestion_academica.dto.AsignaturaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_academica.servicios.AsignaturaServicio;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
@CrossOrigin(origins = "http://localhost:7568")
public class AsignaturaControlador {
    private final AsignaturaServicio asignaturaServicio;

    public AsignaturaControlador(AsignaturaServicio asignaturaServicio) {
        this.asignaturaServicio = asignaturaServicio;
    }

    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> obtenerTodas() {
        return ResponseEntity.ok(asignaturaServicio.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(asignaturaServicio.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AsignaturaDTO> crear(@RequestBody AsignaturaDTO dto) {
        return ResponseEntity.status(201).body(asignaturaServicio.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> actualizar(@PathVariable Long id, @RequestBody AsignaturaDTO dto) {
        try {
            return ResponseEntity.ok(asignaturaServicio.modificar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asignaturaServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

