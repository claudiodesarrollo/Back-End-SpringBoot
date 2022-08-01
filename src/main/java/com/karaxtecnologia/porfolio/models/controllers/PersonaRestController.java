package com.karaxtecnologia.porfolio.models.controllers;


import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.karaxtecnologia.porfolio.models.entity.Persona;
import com.karaxtecnologia.porfolio.models.services.IPersonaService;
import com.karaxtecnologia.porfolio.models.services.IUploadFileService;

@CrossOrigin(origins = { "https://porfolioclaudioq.web.app", "*" })
@RestController
@RequestMapping("/porfolio")
public class PersonaRestController {

	@Autowired
	private IPersonaService personaService;
	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("/personas")
	public List<Persona> index() {
		return personaService.findAll();
	}

	@GetMapping("/personas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();
		try {
			persona = personaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (persona == null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("personas")
	public ResponseEntity<?> create(@RequestBody Persona persona) {
		Persona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			personaNew = personaService.save(persona);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Usuario ha sido creado con éxito!");
		response.put("persona", personaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/personas/{id}")
	public ResponseEntity<?> update(@RequestBody Persona persona, @PathVariable Long id) {

		Persona personaActual = personaService.findById(id);
		Persona personaUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if (personaActual == null) {
			response.put("mensaje", "Error nose puedo editar ,El usuario ID:"
					.concat(id.toString().concat("no existe en labase de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			/*
			 * personaActual.setApellido(persona.getApellido());
			 * personaActual.setDni(persona.getDni());
			 * personaActual.setEmail(persona.getEmail());
			 * personaActual.setFoto(persona.getFoto());
			 * personaActual.setTelefono(persona.getTelefono());
			 * personaActual.setAcerca(persona.getAcerca());
			 * personaActual.setBanner(persona.getBanner());
			 * personaActual.setCiudad(persona.getCiudad());
			 * personaActual.setExperiencias(persona.getExperiencias());
			 * personaActual.setFormaciones(persona.getFormaciones());
			 * personaActual.setProyectos(persona.getProyectos());
			 */
			personaActual = persona;
			personaUpdate = personaService.save(personaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con exito!");
		response.put("usuario", personaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("personas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		try {
			personaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/personas/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Persona persona = personaService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = persona.getFoto();
			uploadService.eliminar(nombreFotoAnterior);	
			persona.setFoto(nombreArchivo);
			personaService.save(persona);
			response.put("persona", persona);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
