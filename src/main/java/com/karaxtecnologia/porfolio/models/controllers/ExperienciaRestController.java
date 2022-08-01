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

import com.karaxtecnologia.porfolio.models.entity.Experiencia;
import com.karaxtecnologia.porfolio.models.services.IExperienciaService;

@CrossOrigin(origins = { "https://porfolioclaudioq.web.app", "*" })
@RestController
@RequestMapping("/porfolio")

public class ExperienciaRestController {
	@Autowired
	private IExperienciaService experienciaService;

	@GetMapping("/experiencias")
	public List<Experiencia> index() {
		return experienciaService.findAll();
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/experiencias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Experiencia experiencia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			experiencia = experienciaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (experiencia == null) {
			response.put("mensaje", "Experiencia ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Experiencia>(experiencia, HttpStatus.OK);

	}

	@Secured("ROLE_ADMIN")
	@PostMapping("experiencias")
	public ResponseEntity<?> create(@RequestBody Experiencia experiencia) {
		Experiencia experienciaNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			experienciaNew = experienciaService.save(experiencia);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Experiencia creado con éxito!");
		response.put("experiencia", experienciaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/experiencias/{id}")
	public ResponseEntity<?> update(@RequestBody Experiencia experiencia, @PathVariable Long id) {
		Experiencia experienciaActual = experienciaService.findById(id);
		Experiencia experienciaUpdate = null;
		Map<String, Object> response = new HashMap<>();

		if (experienciaActual == null) {
			response.put("mensaje",
					"Error nose puedo editar ,Skill ID:".concat(id.toString().concat("no existe en labase de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			experienciaActual.setDescripcion(experiencia.getDescripcion());
			experienciaActual.setEmpleador(experiencia.getEmpleador());
			experienciaActual.setFechaFin(experiencia.getFechaFin());
			experienciaActual.setFechaInicio(experiencia.getFechaInicio());
			experienciaActual.setLocalidad(experiencia.getLocalidad());
			experienciaActual.setPuesto(experiencia.getPuesto());
			experienciaActual.setExperiencia_id(experiencia.getExperiencia_id());
			experienciaUpdate = experienciaService.save(experienciaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Skill ha sido actualizado con exito!");
		response.put("experiencia", experienciaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("experiencias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			experienciaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Experiencia ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
