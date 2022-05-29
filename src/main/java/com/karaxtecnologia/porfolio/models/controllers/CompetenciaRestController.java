package com.karaxtecnologia.porfolio.models.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.karaxtecnologia.porfolio.models.entity.Competencia;

import com.karaxtecnologia.porfolio.models.services.ICompetenciaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/porfolio")
public class CompetenciaRestController {
	@Autowired
	private ICompetenciaService competenciaService;
	@GetMapping("/competencias")
	public List<Competencia> index(){
		return competenciaService.findAll();
	}
	
	@GetMapping("/competencias/{id}")
	public Competencia show(@PathVariable Long id) {
		return competenciaService.findById(id);
	}
	
	@PostMapping("competencias")
	@ResponseStatus(HttpStatus.CREATED)
	public Competencia create(@RequestBody Competencia competencia) {
		return competenciaService.save(competencia);
	}
	
	@PutMapping("/competencias/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Competencia update (@RequestBody Competencia competencia ,@PathVariable Long id){
		Competencia competenciaActual = competenciaService.findById(id);
		competenciaActual.setHabilidad(competencia.getHabilidad());
		competenciaActual.setNivel(competencia.getNivel());
		competenciaActual.setPorciento(competencia.getPorciento());
		return competenciaService.save(competenciaActual);
	}
	
	@DeleteMapping("competencias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		competenciaService.delete(id);
	}
	
	
	

}