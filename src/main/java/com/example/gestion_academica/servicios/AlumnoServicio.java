package com.example.gestion_academica.servicios;

import com.example.gestion_academica.dto.AlumnoDTO;
import com.example.gestion_academica.dto.NotaDTO;
import com.example.gestion_academica.modelos.Alumno;
import com.example.gestion_academica.modelos.Nota;
import com.example.gestion_academica.repositorios.AlumnoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServicio {
    private final AlumnoRepositorio alumnoRepositorio;

    public AlumnoServicio(AlumnoRepositorio alumnoRepositorio) {
        this.alumnoRepositorio = alumnoRepositorio;
    }

    // Obtener todos los alumnos
    public List<AlumnoDTO> obtenerTodos() {
        return alumnoRepositorio.findAll().stream()
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getCedula(),
                        alumno.getNombres(),
                        alumno.getApellidos(),
                        alumno.getEmail(),
                        alumno.getTelefono(),
                        alumno.getEdad(),
                        alumno.getNotas() != null ?
                                alumno.getNotas().stream()
                                        .map(this::convertirNotaADTO)
                                        .collect(Collectors.toList())
                                : List.of()
                ))
                .collect(Collectors.toList());
    }

    // Obtener alumno por ID
    public AlumnoDTO obtenerPorId(Long id) {
        Alumno alumno = alumnoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        List<NotaDTO> notaDTOs = alumno.getNotas() != null ?
                alumno.getNotas().stream()
                        .map(this::convertirNotaADTO)
                        .collect(Collectors.toList())
                : List.of();

        return new AlumnoDTO(
                alumno.getId(),
                alumno.getCedula(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getEmail(),
                alumno.getTelefono(),
                alumno.getEdad(),
                notaDTOs
        );
    }

    // Crear nuevo alumno
    public AlumnoDTO crear(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setCedula(dto.getCedula());
        alumno.setNombres(dto.getNombres());
        alumno.setApellidos(dto.getApellidos());
        alumno.setEmail(dto.getEmail());
        alumno.setTelefono(dto.getTelefono());
        alumno.setEdad(dto.getEdad());
        alumnoRepositorio.save(alumno);
        return obtenerPorId(alumno.getId());
    }

    // Modificar alumno existente
    public AlumnoDTO modificar(Long id, AlumnoDTO dto) {
        Alumno alumno = alumnoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumno.setCedula(dto.getCedula());
        alumno.setNombres(dto.getNombres());
        alumno.setApellidos(dto.getApellidos());
        alumno.setEmail(dto.getEmail());
        alumno.setTelefono(dto.getTelefono());
        alumno.setEdad(dto.getEdad());

        alumnoRepositorio.save(alumno);
        return obtenerPorId(alumno.getId());
    }

    // Eliminar alumno
    public void eliminar(Long id) {
        alumnoRepositorio.deleteById(id);
    }

    // Método auxiliar para convertir Nota a NotaDTO
    private NotaDTO convertirNotaADTO(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getAlumno().getNombres() + " " + nota.getAlumno().getApellidos(),
                nota.getAsignatura().getNombre(),
                nota.getCalificacion(),
                nota.getFechaRegistro() != null ? nota.getFechaRegistro().toString() : ""
        );
    }
}
