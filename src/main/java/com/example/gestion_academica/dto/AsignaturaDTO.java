package com.example.gestion_academica.dto;

public class AsignaturaDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private int creditos;
    private String semestre;

    public AsignaturaDTO() {}

    public AsignaturaDTO(Long id, String nombre, String codigo, int creditos, String semestre) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.semestre = semestre;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
}
