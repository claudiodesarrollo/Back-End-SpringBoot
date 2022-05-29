package com.karaxtecnologia.porfolio.models.services;

import java.util.List;

import com.karaxtecnologia.porfolio.models.entity.Competencia;

public interface ICompetenciaService {
	public List<Competencia> findAll();

	public Competencia findById(Long id);

	public Competencia save(Competencia competencia);

	public void delete(Long id);
}
