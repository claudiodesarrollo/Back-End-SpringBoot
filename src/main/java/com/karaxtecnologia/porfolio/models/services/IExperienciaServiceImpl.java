package com.karaxtecnologia.porfolio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaxtecnologia.porfolio.models.dao.IExperienciaDao;
import com.karaxtecnologia.porfolio.models.entity.Experiencia;

@Service
public class IExperienciaServiceImpl implements IExperienciaService {
	@Autowired
	private IExperienciaDao experienciaDao;

	@Override
	public List<Experiencia> findAll() {
	
		return (List<Experiencia>) experienciaDao.findAll();
	}

	@Override
	public Experiencia findById(Long id) {
	
		return experienciaDao.findById(id).orElse(null);
	}

	@Override
	public Experiencia save(Experiencia experiencia) {

		return experienciaDao.save(experiencia);
	}

	@Override
	public void delete(Long id) {

		experienciaDao.deleteById(id);
	}

}
