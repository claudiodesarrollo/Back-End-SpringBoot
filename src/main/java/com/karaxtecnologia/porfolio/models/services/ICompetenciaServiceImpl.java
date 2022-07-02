package com.karaxtecnologia.porfolio.models.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaxtecnologia.porfolio.models.dao.ICompetenciaDao;
import com.karaxtecnologia.porfolio.models.entity.Competencia;

@Service
public class ICompetenciaServiceImpl implements ICompetenciaService {
	@Autowired
	private ICompetenciaDao competenciaDao;

	@Override
	public List<Competencia> findAll() {
	
		return (List<Competencia>) competenciaDao.findAll();
	}

	@Override
	public Competencia findById(Long id) {
	
		return competenciaDao.findById(id).orElse(null);
	}

	@Override
	public Competencia save(Competencia competencia) {
	
		return competenciaDao.save(competencia);
	}

	@Override
	public void delete(Long id) {

		competenciaDao.deleteById(id);
	}



}
