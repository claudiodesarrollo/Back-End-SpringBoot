package com.karaxtecnologia.porfolio.models.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.karaxtecnologia.porfolio.models.entity.Persona;
import com.karaxtecnologia.porfolio.models.services.IPersonaService;

@CrossOrigin(origins = { "http://localhost:4200" },allowedHeaders = "*")
@RestController
@RequestMapping("/porfolio")
public class PersonaRestController {

	@Autowired
	private IPersonaService personaService;

	@GetMapping("/personas")
	public List<Persona> index() {
		return personaService.findAll();
	}

	@GetMapping("/personas/{id}")
	public Persona show(@PathVariable Long id) {
		return personaService.findById(id);
	}

	@PostMapping("personas")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona create(@RequestBody Persona persona) {
		return personaService.save(persona);
	}

	@PutMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona update(@RequestBody Persona persona, @PathVariable Long id) {

		Persona personaActual = personaService.findById(id);

		personaActual.setApellido(persona.getApellido());
		personaActual.setDni(persona.getDni());
		personaActual.setEmail(persona.getEmail());
		personaActual.setFoto(persona.getFoto());
		personaActual.setTelefono(persona.getTelefono());
		personaActual.setAcerca(persona.getAcerca());
		personaActual.setBanner(persona.getBanner());
		personaActual.setCompetencias(persona.getCompetencias());
		personaActual.setExperiencias(persona.getExperiencias());
		personaActual.setFormaciones(persona.getFormaciones());
		personaActual.setProyectos(persona.getProyectos());

		return personaService.save(personaActual);
	}

	@DeleteMapping("personas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personaService.delete(id);
	}

	@PostMapping("/personas/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Persona persona = personaService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString()+"-"+archivo.getOriginalFilename();
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen " + nombreArchivo);

				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior = persona.getFoto();
			if(nombreFotoAnterior!=null && nombreFotoAnterior.length()>0){
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists()&&archivoFotoAnterior.canRead()){
					archivoFotoAnterior.delete();
				}
			}
			persona.setFoto(nombreArchivo);
			personaService.save(persona);
			response.put("persona",persona);
			response.put("mensaje", "El cliente ha sido actualizado con éxito! " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
	
			e.printStackTrace();
		}
		if(!recurso.exists()&&!recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen "+nombreFoto);
			
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
	}

}

