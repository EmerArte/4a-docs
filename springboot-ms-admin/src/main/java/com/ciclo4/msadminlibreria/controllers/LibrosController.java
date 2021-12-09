package com.ciclo4.msadminlibreria.controllers;

import com.ciclo4.msadminlibreria.exceptions.LibrosNotFoundException;
import com.ciclo4.msadminlibreria.models.Libros;
import com.ciclo4.msadminlibreria.repositories.LibrosRepository;
import com.ciclo4.msadminlibreria.services.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class LibrosController {
    private final String URLBucket= "https://mybuckedfordjango.s3.amazonaws.com/";
    private final LibrosRepository librosRepository;
    @Autowired
    private AWSS3Service awss3Service;

    public LibrosController(LibrosRepository librosRepository) {
        this.librosRepository = librosRepository;
    }
    @GetMapping("/libros/{titulo}")
    Libros getLibros(@PathVariable String titulo){
        String sent = titulo.replace("_"," ");
        return librosRepository.findById(sent).orElseThrow(() -> new LibrosNotFoundException("No se encontro una cuenta con el username: " + titulo));
    }
    @PostMapping("/libros")
    Libros newLibros(@RequestPart MultipartFile file, @ModelAttribute Libros libros){
        String name = awss3Service.uploadFile(file);
       if(name==null){
           throw new LibrosNotFoundException("No se subío una imagen correspondiente");
       }
      String urlresult =  name.replace(" ", "+");
       libros.setImagen(URLBucket+urlresult);
        return librosRepository.save(libros);
    }
    @GetMapping("/libros/getall")
    List<Libros> getAllLibros(){
        return librosRepository.findAll();
    }
    @DeleteMapping("/libros/delete/{id}")
    ResponseEntity<String> delete(@PathVariable String id){
        librosRepository.deleteById(id);
        return new ResponseEntity<String>("Libro eliminado correctamente", HttpStatus.OK);
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
