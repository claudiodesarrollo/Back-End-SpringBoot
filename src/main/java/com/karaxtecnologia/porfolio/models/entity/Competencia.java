package com.karaxtecnologia.porfolio.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competencias")
public class Competencia implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String habilidad;
	private String nivel;
	private Long porciento;
	private int competencia_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Long getPorciento() {
		return porciento;
	}

	public void setPorciento(Long porciento) {
		this.porciento = porciento;
	}
	public int getCompetencia_id() {
		return competencia_id;
	}

	public void setCompetencia_id(int i) {
		this.competencia_id = i;
	}

	private static final long serialVersionUID = 1L;

}
