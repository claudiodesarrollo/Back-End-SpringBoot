package com.karaxtecnologia.porfolio.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "experiencias")
public class Experiencia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String puesto;
	private String empleador;
	private String localidad;
	private String descripcion;
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	private int experiencia_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getEmpleador() {
		return empleador;
	}

	public void setEmpleador(String empleador) {
		this.empleador = empleador;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getExperiencia_id() {
		return experiencia_id;
	}

	public void setExperiencia_id(int experiencia_id) {
		this.experiencia_id = experiencia_id;
	}

	private static final long serialVersionUID = 1L;

}
