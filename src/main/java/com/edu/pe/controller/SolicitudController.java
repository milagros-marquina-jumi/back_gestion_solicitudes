package com.edu.pe.controller;

import com.edu.pe.models.Solicitud;
import com.edu.pe.repository.service.ExportService;
import com.edu.pe.repository.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/solicitud")
@CrossOrigin
public class SolicitudController {

	@Autowired
    private SolicitudService solicitudService;
	
	@Autowired
	private ExportService exportService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Solicitud> listarTodos() {
        return solicitudService.listarTodos();
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
	public Mono<Solicitud> buscarPorId(@PathVariable("id") Integer id) {
		return solicitudService.buscarPorId(id)
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrado")));
	}

    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Solicitud> guardar(@RequestBody Solicitud solicitud) {
    	return solicitudService.guardar(solicitud);
    }

    @GetMapping("/exportar")
    public Mono<ResponseEntity<byte[]>> exportarSolicitudes() {
        return solicitudService.listarTodos()
                .collectList()
                .flatMap(solicitudes -> exportService.exportarSolicitudesCSV(solicitudes)) 
                .map(csvData -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=solicitudes.csv")
                        .body(csvData));  
    }
}