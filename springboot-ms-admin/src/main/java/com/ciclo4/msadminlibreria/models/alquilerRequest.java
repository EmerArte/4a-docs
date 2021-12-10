package com.ciclo4.msadminlibreria.models;
import org.springframework.data.annotation.Id;
import java.util.Date;

public class alquilerRequest {
    @Id
    private String id;
    private String usuario;
    private String libro;
    private Date fecha_de_inicio;
    private Date fecha_de_fin;
    private boolean activo;

    public alquilerRequest(String id, String usuario, String libro, Date fecha_de_inicio, Date fecha_de_fin, boolean activo) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_de_inicio = fecha_de_inicio;
        this.fecha_de_fin = fecha_de_fin;
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public Date getFecha_de_inicio() {
        return fecha_de_inicio;
    }

    public void setFecha_de_inicio(Date fecha_de_inicio) {
        this.fecha_de_inicio = fecha_de_inicio;
    }

    public Date getFecha_de_fin() {
        return fecha_de_fin;
    }

    public void setFecha_de_fin(Date fecha_de_fin) {
        this.fecha_de_fin = fecha_de_fin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}