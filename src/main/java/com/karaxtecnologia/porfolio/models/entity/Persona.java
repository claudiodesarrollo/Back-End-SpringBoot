package com.karaxtecnologia.porfolio.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Integer dni;

	private String nombre;

	private String apellido;
	@Column(nullable=false, unique=true)
	private String email;

	private String telefono;

	private String foto;
	
	private String banner;

	private String acerca;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "experiencia_id")
	private List<Experiencia> experiencias;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "formacion_id")
	private List<Formacion> formaciones;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "competencia_id")
	private List<Competencia> competencias;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "proyecto_id")
	private List<Proyecto> proyectos;

	public Persona() {
		experiencias = new ArrayList<>();
		formaciones = new ArrayList<>();
		competencias = new ArrayList<>();
		proyectos = new ArrayList<>();
	}

	public List<Formacion> getFormaciones() {
		return formaciones;
	}

	public void setFormaciones(List<Formacion> formaciones) {
		this.formaciones = formaciones;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getAcerca() {
		return acerca;
	}

	public void setAcerca(String acerca) {
		this.acerca = acerca;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	private static final long serialVersionUID = 1L;
}
