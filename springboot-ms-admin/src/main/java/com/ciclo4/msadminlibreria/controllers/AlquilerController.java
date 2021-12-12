package com.ciclo4.msadminlibreria.controllers;

import com.ciclo4.msadminlibreria.exceptions.AlquilerNoDisponibleException;
import com.ciclo4.msadminlibreria.exceptions.LibrosNotFoundException;
import com.ciclo4.msadminlibreria.models.alquilerRequest;
import com.ciclo4.msadminlibreria.models.Libros;
import com.ciclo4.msadminlibreria.repositories.AlquilerRepository;
import com.ciclo4.msadminlibreria.repositories.LibrosRepository;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AlquilerController {
    private final LibrosRepository librosRepository;
    private final AlquilerRepository alquilerRepository;
    DateFormat formateadorFechaCorta = DateFormat.getDateInstance(DateFormat.SHORT);
    Calendar calendar = Calendar.getInstance();
    public AlquilerController(LibrosRepository librosRepository, AlquilerRepository alquilerRepository) {
        this.librosRepository = librosRepository;
        this.alquilerRepository = alquilerRepository;
    }

    @PostMapping("/alquiler")
    alquilerRequest newAlquiler(@RequestBody alquilerRequest alquiler) {
        Date now = new Date();
        Libros librosDestinity =
                librosRepository.findById(alquiler.getLibro()).orElse(null);
        if (librosDestinity == null)
            throw new LibrosNotFoundException("El libro seleccionado no se encuentra en la db : " + alquiler.getLibro());
        if (!librosDestinity.getDisponible())
            throw new AlquilerNoDisponibleException("Libro no disponible");
        alquiler.setActivo(true);
        alquiler.setFecha_de_inicio(formateadorFechaCorta.format(now));
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        alquiler.setFecha_de_fin(formateadorFechaCorta.format(calendar.getTime()));
        return alquilerRepository.save(alquiler);
    }

    @GetMapping("/alquiler/{usuario}")
    List<alquilerRequest> userAlquiler(@PathVariable String usuario) {

        List<alquilerRequest> alquilerUsuario =
                alquilerRepository.findByUsuario(usuario);
        if(alquilerUsuario.isEmpty()){
            throw new AlquilerNoDisponibleException("No se tienes ningun libro asociado");
        }
        return alquilerUsuario;
    }
    @DeleteMapping("/alquiler/delete/{id}")
    alquilerRequest delete(@PathVariable String id){
        alquilerRequest alquiler = alquilerRepository.findById(id).orElse(null);
        if (alquiler == null)
            throw new LibrosNotFoundException("No se encontro alquiler: " + id);
        alquilerRepository.deleteById(id);
        return alquiler;
    }
    @GetMapping("/alquiler/activos")
    List<alquilerRequest> getAlquieleresActivos(){
        List<alquilerRequest> alquilers = alquilerRepository.findByActivoTrue();
        return alquilers;
    }
    @GetMapping("/alquiler/pendientes")
    List<alquilerRequest> alquileresPendientesPorFinalizar() throws ParseException {
        Date now = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<alquilerRequest> pendientes = new ArrayList<>();
        List<alquilerRequest> alquilers = alquilerRepository.findByActivoTrue();
        if(!alquilers.isEmpty()){
            for (alquilerRequest a:
                    alquilers) {
                Date fechaFinFormat = formato.parse(a.getFecha_de_fin());
                if(a.isActivo() && now.before(fechaFinFormat)){
                    pendientes.add(a);
                }
            }
            return pendientes;
        }
       return null;

    }

}

