package com.example.gestion_academica.dto;

public class NotaDTO {
    private Long id;
    private String nombreAlumno;
    private String nombreAsignatura;
    private Double calificacion;
    private String fechaRegistro; // como String legible

    public NotaDTO() {}

    public NotaDTO(Long id, String nombreAlumno, String nombreAsignatura, Double calificacion, String fechaRegistro) {
        this.id = id;
        this.nombreAlumno = nombreAlumno;
        this.nombreAsignatura = nombreAsignatura;
        this.calificacion = calificacion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }
    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }
    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
