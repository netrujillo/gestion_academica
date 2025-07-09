package com.example.gestion_academica.servicios;

import com.example.gestion_academica.dto.NotaDTO;
import com.example.gestion_academica.modelos.Alumno;
import com.example.gestion_academica.modelos.Asignatura;
import com.example.gestion_academica.modelos.Nota;
import com.example.gestion_academica.repositorios.AlumnoRepositorio;
import com.example.gestion_academica.repositorios.AsignaturaRepositorio;
import com.example.gestion_academica.repositorios.NotaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaServicio {
    private final NotaRepositorio notaRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final AsignaturaRepositorio asignaturaRepositorio;

    public NotaServicio(NotaRepositorio notaRepositorio, AlumnoRepositorio alumnoRepositorio, AsignaturaRepositorio asignaturaRepositorio) {
        this.notaRepositorio = notaRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.asignaturaRepositorio = asignaturaRepositorio;
    }

    // Obtener todas las notas
    public List<NotaDTO> obtenerTodas() {
        return notaRepositorio.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener nota por ID
    public NotaDTO obtenerPorId(Long id) {
        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        return convertirADTO(nota);
    }

    // Crear nota nueva
    public NotaDTO crear(Long alumnoId, Long asignaturaId, Double calificacion) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Asignatura asignatura = asignaturaRepositorio.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        Nota nota = new Nota();
        nota.setAlumno(alumno);
        nota.setAsignatura(asignatura);
        nota.setCalificacion(calificacion);
        nota.setFechaRegistro(LocalDate.now());

        notaRepositorio.save(nota);
        return convertirADTO(nota);
    }

    // Modificar nota
    public NotaDTO modificar(Long id, Double nuevaCalificacion) {
        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

        nota.setCalificacion(nuevaCalificacion);
        notaRepositorio.save(nota);

        return convertirADTO(nota);
    }

    // Eliminar nota
    public void eliminar(Long id) {
        notaRepositorio.deleteById(id);
    }

    // Metodo auxiliar
    private NotaDTO convertirADTO(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getAlumno().getNombres() + " " + nota.getAlumno().getApellidos(),
                nota.getAsignatura().getNombre(),
                nota.getCalificacion(),
                nota.getFechaRegistro() != null ? nota.getFechaRegistro().toString() : ""
        );
    }
}
