package com.ciclo4.msadminlibreria.repositories;
import com.ciclo4.msadminlibreria.models.Libros;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface LibrosRepository extends MongoRepository <Libros, String> { }
