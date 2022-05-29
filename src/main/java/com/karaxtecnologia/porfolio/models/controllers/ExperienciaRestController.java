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

import com.karaxtecnologia.porfolio.models.entity.Experiencia;
import com.karaxtecnologia.porfolio.models.services.IExperienciaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/porfolio")

public class ExperienciaRestController {
	@Autowired
	private IExperienciaService experienciaService;
	
	@GetMapping("/experiencias")
	public List<Experiencia> index(){
		return experienciaService.findAll();
	}
	
	@GetMapping("/experiencias/{id}")
	public Experiencia show(@PathVariable Long id) {
		return experienciaService.findById(id);
	}
	@PostMapping("experiencias")
	@ResponseStatus(HttpStatus.CREATED)
	public Experiencia create(@RequestBody Experiencia experiencia) {
		return experienciaService.save(experiencia);
	}
	
	@PutMapping("/experiencias/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Experiencia update (@RequestBody Experiencia experiencia ,@PathVariable Long id){
		
		Experiencia experienciaActual = experienciaService.findById(id);
		
		experienciaActual.setDescripcion(experiencia.getDescripcion());
		experienciaActual.setEmpleador(experiencia.getEmpleador());
		experienciaActual.setFechaFin(experiencia.getFechaFin());
		experienciaActual.setFechaInicio(experiencia.getFechaInicio());
		experienciaActual.setLocalidad(experiencia.getLocalidad());
		experienciaActual.setPuesto(experiencia.getPuesto());
		
		return experienciaService.save(experienciaActual);
	}
	
	@DeleteMapping("experiencias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		experienciaService.delete(id);
	}
	
}
