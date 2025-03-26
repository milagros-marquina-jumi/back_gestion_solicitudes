package com.edu.pe.repository;

import com.edu.pe.models.Contacto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IContactoRepository extends ReactiveCrudRepository<Contacto, Integer> {
    
    public Flux<Contacto> findByIdSolicitud(Integer idSolicitud);
    
}