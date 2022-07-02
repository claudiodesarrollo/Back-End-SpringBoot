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

import com.karaxtecnologia.porfolio.models.entity.Proyecto;
import com.karaxtecnologia.porfolio.models.services.IProyectoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/porfolio")
public class ProyectoRestController {
	@Autowired
	private IProyectoService proyectoService;

	@GetMapping("/proyectos")
	public List<Proyecto> index() {
		return proyectoService.findAll();
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/proyectos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Proyecto proyecto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proyecto = proyectoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (proyecto == null) {
			response.put("mensaje", "Proyecto ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("proyectos")
	public ResponseEntity<?> create(@RequestBody Proyecto proyecto) {
		Proyecto proyectoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proyectoNew = proyectoService.save(proyecto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Proyecto creado con éxito!");
		response.put("proyecto", proyectoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/proyectos/{id}")
	public ResponseEntity<?> update(@RequestBody Proyecto proyecto, @PathVariable Long id) {
		Proyecto proyectoActual = proyectoService.findById(id);
		Proyecto proyectoUpdate = null;
		Map<String, Object> response = new HashMap<>();

		if (proyectoActual == null) {
			response.put("mensaje", "Error nose puedo editar ,Proyecto ID:"
					.concat(id.toString().concat("no existe en labase de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			proyectoActual.setDescripcion(proyecto.getDescripcion());
			proyectoActual.setImagen(proyecto.getImagen());
			proyectoActual.setLink(proyecto.getLink());
			proyectoActual.setTitulo(proyecto.getTitulo());
			proyectoUpdate = proyectoService.save(proyectoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Proyecto ha sido actualizado con exito!");
		response.put("proyecto", proyectoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("proyectos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			proyectoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Proyecto ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
