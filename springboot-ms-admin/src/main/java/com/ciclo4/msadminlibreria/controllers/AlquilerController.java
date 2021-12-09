package com.ciclo4.msadminlibreria.controllers;

import com.ciclo4.msadminlibreria.exceptions.LibrosNotFoundException;
import com.ciclo4.msadminlibreria.exceptions.AlquilerNoDisponibleException;
import com.ciclo4.msadminlibreria.models.Libros;
import com.ciclo4.msadminlibreria.models.Alquiler;
import com.ciclo4.msadminlibreria.repositories.AlquilerRepository;
import com.ciclo4.msadminlibreria.repositories.LibrosRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AlquilerController {
    private final LibrosRepository librosRepository;
    private final AlquilerRepository alquilerRepository;

    public AlquilerController(LibrosRepository librosRepository, AlquilerRepository alquilerRepository) {
        this.librosRepository = librosRepository;
        this.alquilerRepository = alquilerRepository;
    }

    @PostMapping("/alquiler")
    Alquiler newAlquiler(@RequestBody Alquiler alquiler) {
        Libros librosOrigin =
                librosRepository.findById(alquiler.getUsuario()).orElse(null);
        Libros librosDestinity =
                librosRepository.findById(alquiler.getLibro()).orElse(null);
        if (librosOrigin == null)
            throw new LibrosNotFoundException("No se encontro un libro con el titulo: " + alquiler.getUsuario());
        if (librosDestinity == null)
            throw new LibrosNotFoundException("No se encontro un libro con el titulo: " + alquiler.getLibro());
        if (librosOrigin.getDisponible() == alquiler.isActivo())
            throw new AlquilerNoDisponibleException("Libro no disponible");
        /// accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        ///librosOrigin.setLastChange(new Date());
        //librosRepository.save(librosOrigin);
        ///accountDestinity.setBalance(accountDestinity.getBalance() +
        //transaction.getValue());
        //accountDestinity.setLastChange(new Date());
        //accountRepository.save(accountDestinity);
        alquiler.setFecha_de_inicio(new Date());
        return alquilerRepository.save(alquiler);
    }

    @GetMapping("/alquiler/{usuario}")
    List<Alquiler> userAlquiler(@PathVariable String usuario) {
        Libros userLibros = librosRepository.findById(usuario).orElse(null);
        if (userLibros == null)
            throw new LibrosNotFoundException("No se encontro usuario: " + usuario);
        List<Alquiler> alquilerUsuario =
                alquilerRepository.findByUsuario(usuario);
        List<Alquiler> alquilerLibro =
                alquilerRepository.findByLibro(usuario);
        List<Alquiler> alquilers = Stream.concat(alquilerUsuario.stream(),
                alquilerLibro.stream()).collect(Collectors.toList());
        return alquilers;
    }
    @DeleteMapping("/alquiler/delete/{id}")
    Alquiler delete(@PathVariable String id){
        Alquiler alquiler = alquilerRepository.findById(id).orElse(null);
        if (alquiler == null)
            throw new LibrosNotFoundException("No se encontro alquiler: " + id);
        alquilerRepository.deleteById(id);
        return alquiler;
    }
    @GetMapping("/alquiler/vencidos")
    List<Alquiler> getAlquilerVencidos(){
        List<Alquiler> alquilers = alquilerRepository.findAlquilerByActivoTrue();
        return alquilers;
    }

}

