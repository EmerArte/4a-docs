package com.ciclo4.msadminlibreria.models;
import org.springframework.data.annotation.Id;
import java.util.Date;


public class Alquiler {
    @Id
    private String usuario;
    private String libro;
    private Date fecha_de_inicio;
    private Boolean disponible_dos;


    public Alquiler(String usuario, String libro, Date fecha_de_inicio) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_de_inicio = fecha_de_inicio;
        this. disponible_dos = disponible_dos;
    }

    public Boolean getDisponible_dos() {
        return disponible_dos;
    }

    public void setDisponible_dos(Boolean disponible_dos) {
        this.disponible_dos = disponible_dos;
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
}
