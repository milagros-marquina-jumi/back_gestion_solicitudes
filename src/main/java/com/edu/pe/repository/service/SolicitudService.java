package com.edu.pe.repository.service;

import com.edu.pe.models.Contacto;
import com.edu.pe.models.Solicitud;
import com.edu.pe.repository.IContactoRepository;
import com.edu.pe.repository.ISolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SolicitudService {

	@Autowired
	private ISolicitudRepository solicitudRepository;

	@Autowired
	private IContactoRepository contactoRepository;

	public Mono<Solicitud> guardar(Solicitud solicitud) {
	    return solicitudRepository.save(solicitud)
	            .flatMap(savedSolicitud -> {
	                Flux<Mono<Contacto>> contactosGuardados = Flux.fromIterable(solicitud.getContactos())
	                        .map(contacto -> {
	                            contacto.setIdSolicitud(savedSolicitud.getId());  
	                            return contactoRepository.save(contacto); 
	                        });
	                return Flux.concat(contactosGuardados)
	                        .then(Mono.just(savedSolicitud));  
	            });
	}



	public Flux<Solicitud> listarTodos() {
		return solicitudRepository.findAll().flatMap(
				solicitud -> contactoRepository.findByIdSolicitud(solicitud.getId()).collectList().map(contactos -> {
					solicitud.setContactos(contactos);
					return solicitud;
				}));
	}

	public Mono<Solicitud> buscarPorId(Integer id) {
		return solicitudRepository.findById(id)
				.flatMap(solicitud -> contactoRepository.findByIdSolicitud(id).collectList().map(contactos -> {
					solicitud.setContactos(contactos);
					return solicitud;
				}));
	}
}