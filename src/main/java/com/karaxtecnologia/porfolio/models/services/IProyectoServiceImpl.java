package com.karaxtecnologia.porfolio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaxtecnologia.porfolio.models.dao.IProyectoDao;

import com.karaxtecnologia.porfolio.models.entity.Proyecto;

@Service
public class IProyectoServiceImpl implements IProyectoService {
	@Autowired
	private IProyectoDao proyectoDao;

	@Override
	public List<Proyecto> findAll() {

		return (List<Proyecto>) proyectoDao.findAll();
	}

	@Override
	public Proyecto findById(Long id) {

		return proyectoDao.findById(id).orElse(null);
	}

	@Override
	public Proyecto save(Proyecto proyecto) {
	
		return proyectoDao.save(proyecto);
	}

	@Override
	public void delete(Long id) {

		proyectoDao.deleteById(id);
	}

}
