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

import com.karaxtecnologia.porfolio.models.entity.Proyecto;
import com.karaxtecnologia.porfolio.models.services.IProyectoService;

@CrossOrigin(origins= {"http://localhost:4200"},allowedHeaders = "*")
@RestController
@RequestMapping("/porfolio")
public class ProyectoRestController {
	@Autowired
	private IProyectoService proyectoService;
	
	@GetMapping("/proyectos")
	public List<Proyecto> index(){
		return proyectoService.findAll();
	}
	
	@GetMapping("/proyectos/{id}")
	public Proyecto show(@PathVariable Long id) {
		return proyectoService.findById(id);
	}
	@PostMapping("proyectos")
	@ResponseStatus(HttpStatus.CREATED)
	public Proyecto create(@RequestBody Proyecto proyecto) {
		return proyectoService.save(proyecto);
	}
	
	@PutMapping("/proyectos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proyecto update (@RequestBody Proyecto proyecto ,@PathVariable Long id){
		Proyecto proyectoActual = proyectoService.findById(id);
		
		proyectoActual.setDescripcion(proyecto.getDescripcion());
		proyectoActual.setImagen(proyecto.getImagen());
		proyectoActual.setLink(proyecto.getLink());
		proyectoActual.setTitulo(proyecto.getTitulo());
		
		return proyectoService.save(proyectoActual);
		
	}
	
	@DeleteMapping("proyectos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		proyectoService.delete(id);
	}
	
	
	
}
