package com.edu.pe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import java.util.List;

@Table("solicitud")
public class Solicitud {
	@Id
	@Column("id_solicitud")
	private Integer id;

	@Column("marca")
	private String marca;

	@Column("tipo_solicitud")
	private String tipoSolicitud;

	@Column("fecha_envio")
	private LocalDate fechaEnvio;

	@Column("numero_contacto")
	private String numeroContacto;

	@Column("nombre_contacto")
	private String nombreContacto;

	@Transient 
	private List<Contacto> contactos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public LocalDate getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDate fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", marca=" + marca + ", tipoSolicitud=" + tipoSolicitud + ", fechaEnvio="
				+ fechaEnvio + ", numeroContacto=" + numeroContacto + ", nombreContacto=" + nombreContacto
				+ ", contactos=" + contactos + "]";
	}

	
}
