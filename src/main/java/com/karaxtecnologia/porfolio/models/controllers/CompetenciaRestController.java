package com.karaxtecnologia.porfolio.models.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karaxtecnologia.porfolio.models.entity.Competencia;
import com.karaxtecnologia.porfolio.models.services.ICompetenciaService;

@CrossOrigin(origins= {"https://porfolioclaudioq.web.app","*"})
@RestController
@RequestMapping("/porfolio")
public class CompetenciaRestController {
	@Autowired
	private ICompetenciaService competenciaService;
	@GetMapping("/competencias")
	public List<Competencia> index(){
		return competenciaService.findAll();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/competencias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Competencia competencia=null;
		Map<String, Object> response = new HashMap<>();
		try {
			competencia = competenciaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (competencia == null) {
			response.put("mensaje", "Skill ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Competencia>(competencia, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/competencias")
	public ResponseEntity<?> create(@RequestBody Competencia competencia) {
		Competencia competenciaNew =null;
		Map<String, Object> response = new HashMap<>();
		try {
			competenciaNew = competenciaService.save(competencia);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Skill creado con éxito!");
		response.put("competencia", competenciaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/competencias/{id}")
	public ResponseEntity<?> update (@RequestBody Competencia competencia ,@PathVariable Long id){
		
		Competencia competenciaActual = competenciaService.findById(id);
		Competencia competenciaUpdate= null;
		Map<String, Object> response = new HashMap<>();

		if (competenciaActual == null) {
			response.put("mensaje", "Error nose puedo editar ,Skill ID:"
					.concat(id.toString().concat("no existe en labase de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			competenciaActual.setHabilidad(competencia.getHabilidad());
			competenciaActual.setNivel(competencia.getNivel());
			competenciaActual.setPorciento(competencia.getPorciento());
			competenciaActual.setCompetencia_id(competencia.getCompetencia_id());
			competenciaUpdate = competenciaService.save(competenciaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Skill ha sido actualizado con exito!");
		response.put("competencia", competenciaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/competencias/{id}")
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		try {	
			competenciaService.delete(id);
		}catch(DataAccessException e){
			response.put("mensaje", "Error al Eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Skill ha sido eliminado con exito!");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	
	

}