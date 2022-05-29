package com.karaxtecnologia.porfolio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaxtecnologia.porfolio.models.dao.IFormacionDao;
import com.karaxtecnologia.porfolio.models.entity.Formacion;

@Service
public class IFormacionServiceImpl implements IFormacionService {
	@Autowired
	private IFormacionDao formacionDao;

	@Override
	public List<Formacion> findAll() {

		return (List<Formacion>) formacionDao.findAll();
	}

	@Override
	public Formacion findById(Long id) {

		return formacionDao.findById(id).orElse(null);
	}

	@Override
	public Formacion save(Formacion formacion) {

		return formacionDao.save(formacion);
	}

	@Override
	public void delete(Long id) {

		formacionDao.deleteById(id);
	}

}
