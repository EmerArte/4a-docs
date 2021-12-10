package com.ciclo4.msadminlibreria.repositories;
import com.ciclo4.msadminlibreria.models.alquilerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface AlquilerRepository extends MongoRepository<alquilerRequest, String> {
    List<alquilerRequest> findByUsuario (String usuario);
    List<alquilerRequest> findByLibro (String libro);
    List<alquilerRequest> findAlquilerByActivoTrue();
}
