package com.example.gestion_academica.servicios;

import com.example.gestion_academica.dto.AsignaturaDTO;
import com.example.gestion_academica.modelos.Asignatura;
import org.springframework.stereotype.Service;
import com.example.gestion_academica.repositorios.AsignaturaRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServicio {
    private final AsignaturaRepositorio asignaturaRepositorio;

    public AsignaturaServicio(AsignaturaRepositorio asignaturaRepositorio) {
        this.asignaturaRepositorio = asignaturaRepositorio;
    }

    // Obtener todas las asignaturas
    public List<AsignaturaDTO> obtenerTodas() {
        return asignaturaRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener asignatura por ID
    public AsignaturaDTO obtenerPorId(Long id) {
        Asignatura asignatura = asignaturaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        return convertirADTO(asignatura);
    }

    // Crear nueva asignatura
    public AsignaturaDTO crear(AsignaturaDTO dto) {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(dto.getNombre());
        asignatura.setCodigo(dto.getCodigo());
        asignatura.setCreditos(dto.getCreditos());
        asignatura.setSemestre(dto.getSemestre());
        asignaturaRepositorio.save(asignatura);
        return convertirADTO(asignatura);
    }

    // Modificar asignatura
    public AsignaturaDTO modificar(Long id, AsignaturaDTO dto) {
        Asignatura asignatura = asignaturaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        asignatura.setNombre(dto.getNombre());
        asignatura.setCodigo(dto.getCodigo());
        asignatura.setCreditos(dto.getCreditos());
        asignatura.setSemestre(dto.getSemestre());
        asignaturaRepositorio.save(asignatura);
        return convertirADTO(asignatura);
    }

    // Eliminar asignatura
    public void eliminar(Long id) {
        asignaturaRepositorio.deleteById(id);
    }

    // Metodo auxiliar
    private AsignaturaDTO convertirADTO(Asignatura asignatura) {
        return new AsignaturaDTO(
                asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getCodigo(),
                asignatura.getCreditos(),
                asignatura.getSemestre()
        );
    }
}
