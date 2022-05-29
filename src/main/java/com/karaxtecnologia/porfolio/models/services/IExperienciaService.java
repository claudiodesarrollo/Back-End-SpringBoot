package com.karaxtecnologia.porfolio.models.services;
import java.util.List;

import com.karaxtecnologia.porfolio.models.entity.Experiencia;

public interface IExperienciaService {
	public List<Experiencia> findAll();

	public Experiencia findById(Long id);

	public Experiencia save(Experiencia experiencia);

	public void delete(Long id);
}
