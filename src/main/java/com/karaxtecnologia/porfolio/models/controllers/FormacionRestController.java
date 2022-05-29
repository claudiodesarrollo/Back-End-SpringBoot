package com.karaxtecnologia.porfolio.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.karaxtecnologia.porfolio.models.entity.Formacion;
import com.karaxtecnologia.porfolio.models.services.IFormacionService;
@CrossOrigin(origins= {"http://localhost:4200"},allowedHeaders = "*")
@RestController
@RequestMapping("/porfolio")
public class FormacionRestController {
	@Autowired
	private IFormacionService formacionService;
	
	@GetMapping("/formaciones")
	public List<Formacion> index(){
		return formacionService.findAll();
	}
	
	@GetMapping("/formaciones/{id}")
	public Formacion show(@PathVariable Long id) {
		return formacionService.findById(id);
	}
	@PostMapping("formaciones")
	@ResponseStatus(HttpStatus.CREATED)
	public Formacion create(@RequestBody Formacion formacion) {
		return formacionService.save(formacion);
	}
	
	@PutMapping("/formaciones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Formacion update (@RequestBody Formacion formacion,@PathVariable Long id){
			Formacion formacionActual = formacionService.findById(id);
			
			formacionActual.setDescripcion(formacion.getDescripcion());
			formacionActual.setFechaFin(formacion.getFechaFin());
			formacionActual.setFechaInicio(formacion.getFechaInicio());
			formacionActual.setInstituto(formacion.getInstituto());
			formacionActual.setLocalidad(formacion.getLocalidad());
			formacionActual.setTitulo(formacion.getTitulo());
			return formacionService.save(formacionActual);
	}
	
	@DeleteMapping("formaciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		formacionService.delete(id);
	}
	
}