package com.karaxtecnologia.porfolio.models.services;
import java.util.List;

import com.karaxtecnologia.porfolio.models.entity.Formacion;

public interface IFormacionService {
	public List<Formacion> findAll();

	public Formacion findById(Long id);

	public Formacion save(Formacion formacion);

	public void delete(Long id);
}
