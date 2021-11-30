package com.ciclo4.msadminlibreria.controllers;

import com.ciclo4.msadminlibreria.exceptions.LibrosNotFoundException;
import com.ciclo4.msadminlibreria.models.Libros;
import com.ciclo4.msadminlibreria.repositories.LibrosRepository;
import org.springframework.web.bind.annotation.*;


public class LibrosController {
    private final LibrosRepository librosRepository;


    public LibrosController(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }
    @GetMapping("/libros/{titulo}")
    Libros getLibros(@PathVariable String titulo){
        return librosRepository.findById(titulo).orElseThrow(() -> new LibrosNotFoundException("No se encontro una cuenta con el username: " + titulo));
    }
    @PostMapping("/Libros")
    Libros newLibros(@RequestBody Libros libros){
        return librosRepository.save(libros);
    }

}
