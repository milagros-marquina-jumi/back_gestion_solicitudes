package com.edu.pe.repository.service;

import com.edu.pe.models.Solicitud;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class ExportService {
	public Mono<byte[]> exportarSolicitudesCSV(List<Solicitud> solicitudes) {
		try {
			System.out.println("solicitydes: " + solicitudes.size());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);

			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Marca", "Tipo Solicitud",
					"Fecha Envio", "Numero Contacto", "Nombre Contacto"));

			for (Solicitud solicitud : solicitudes) {
				csvPrinter.printRecord(solicitud.getId(), solicitud.getMarca(), solicitud.getTipoSolicitud(),
						solicitud.getFechaEnvio(), solicitud.getNumeroContacto(), solicitud.getNombreContacto());
			}
			csvPrinter.flush();
			return Mono.just(out.toByteArray());
		} catch (IOException e) {
			return Mono.error(new RuntimeException("Error al generar el archivo CSV"));
		}
	}
}
