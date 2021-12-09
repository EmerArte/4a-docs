package com.ciclo4.msadminlibreria.models;
import org.springframework.data.annotation.Id;


public class Libros {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private Boolean disponible;
    private String autor;
    private String imagen;
    private Float puntuacion;
    public Libros(String id,String titulo, String descripcion, Boolean disponible, String autor,  Float puntuacion, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion=descripcion;
        this.disponible=disponible;
        this.autor=autor;
        this.puntuacion = puntuacion;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getDisponible() {
        return disponible;
    }
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Float getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(Float puntuacion) {
        this.puntuacion = puntuacion;
    }

    }



