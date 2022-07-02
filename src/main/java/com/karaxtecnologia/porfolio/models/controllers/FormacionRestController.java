package com.karaxtecnologia.porfolio.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karaxtecnologia.porfolio.models.entity.Formacion;
import com.karaxtecnologia.porfolio.models.services.IFormacionService;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/porfolio")
public class FormacionRestController {
	@Autowired
	private IFormacionService formacionService;
	
	@GetMapping("/formaciones")
	public List<Formacion> index(){
		return formacionService.findAll();
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/formaciones/{id}")
	public ResponseEntity<?>  show(@PathVariable Long id) {
		Formacion formacion =null;
		Map<String, Object> response = new HashMap<>();
		try {
			formacion = formacionService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (formacion == null) {
			response.put("mensaje", "Formacion ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Formacion>(formacion, HttpStatus.OK);
	}	
	
	@Secured("ROLE_ADMIN")
	@PostMapping("formaciones")
	public ResponseEntity<?> create(@RequestBody Formacion formacion) {
		Formacion formacionNew =null;
		Map<String, Object> response = new HashMap<>();
		try {
			formacionNew = formacionService.save(formacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Formacion creado con éxito!");
		response.put("formacion", formacionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);		
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/formaciones/{id}")
	public ResponseEntity<?> update (@RequestBody Formacion formacion,@PathVariable Long id){
			Formacion formacionActual = formacionService.findById(id);
			Formacion formacionUpdate = null;
			Map<String, Object> response = new HashMap<>();
			if (formacionActual == null) {
				response.put("mensaje", "Error nose puedo editar ,Formacion ID:"
						.concat(id.toString().concat("no existe en labase de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			try {
				formacionActual.setDescripcion(formacion.getDescripcion());
				formacionActual.setFechaFin(formacion.getFechaFin());
				formacionActual.setFechaInicio(formacion.getFechaInicio());
				formacionActual.setInstituto(formacion.getInstituto());
				formacionActual.setLocalidad(formacion.getLocalidad());
				formacionActual.setTitulo(formacion.getTitulo());
				formacionUpdate = formacionService.save(formacionActual);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "Formacion ha sido actualizado con exito!");
			response.put("formacion", formacionUpdate);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("formaciones/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		try {	
			formacionService.delete(id);
		}catch(DataAccessException e){
			response.put("mensaje", "Error al Eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Formacion ha sido eliminado con exito!");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}