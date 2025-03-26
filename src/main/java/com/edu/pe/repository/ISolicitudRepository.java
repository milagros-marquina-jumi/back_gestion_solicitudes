package com.edu.pe.repository;

import com.edu.pe.models.Solicitud;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudRepository extends ReactiveCrudRepository<Solicitud, Integer> {
	
}