package com.ciclo4.msadminlibreria.controllers;

import com.ciclo4.msadminlibreria.exceptions.LibrosNotFoundException;
import com.ciclo4.msadminlibreria.models.Libros;
import com.ciclo4.msadminlibreria.repositories.LibrosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibrosController {
    private final LibrosRepository librosRepository;
    public LibrosController(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }
    @GetMapping("/libros/{titulo}")
    Libros getLibros(@PathVariable String titulo){
        String sent = titulo.replace("_"," ");
        return librosRepository.findById(sent).orElseThrow(() -> new LibrosNotFoundException("No se encontro una cuenta con el username: " + titulo));
    }
    @PostMapping("/libros")
    Libros newLibros(@RequestBody Libros libros){
        return librosRepository.save(libros);
    }
    @GetMapping("/libros/getall")
    List<Libros> getAllLibros(){
        return librosRepository.findAll();
    }
//    @PutMapping("/libros/{idlibro}")
//    Libros updateLibros(@RequestBody Libros libro, @RequestParam String idlibro){
//        if(librosRepository.findById(idlibro)!=null){
//            return librosRepository.save(libro);
//        }else{
//            return new LibrosNotFoundException("No se encontro una cuenta con el username: " + libro);
//        }
//
//    }

}
