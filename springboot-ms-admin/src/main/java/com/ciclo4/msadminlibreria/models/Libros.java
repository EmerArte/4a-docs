package com.ciclo4.msadminlibreria.models;
import org.springframework.data.annotation.Id;


public class Libros {
    @Id
    private String titulo;
    private String descripcion;
    private String imagen_portada;
    private Boolean disponible;
    private String autor;
    private Float puntuacion;
    public Libros(String titulo, String descripcion, String imagen_portada, Boolean disponible, String autor,  Float puntuacion) {
        this.titulo = titulo;
        this.descripcion=descripcion;
        this.imagen_portada=imagen_portada;
        this.disponible=disponible;
        this.autor=autor;
        this.puntuacion = puntuacion;
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
    public String getImagen_portada() {
        return imagen_portada;
    }
    public void setImagen_portada(String imagen_portada) {
        this.imagen_portada = imagen_portada;
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



