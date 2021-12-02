package com.ciclo4.msadminlibreria.repositories;
import com.ciclo4.msadminlibreria.models.Alquiler;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface AlquilerRepository extends MongoRepository<Alquiler, String> {
    List<Alquiler> findByUsuario (String usuario);
    List<Alquiler> findByLibro (String libro);
}
