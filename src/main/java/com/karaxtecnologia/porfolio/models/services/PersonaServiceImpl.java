package com.karaxtecnologia.porfolio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaxtecnologia.porfolio.models.dao.IPersonaDao;
import com.karaxtecnologia.porfolio.models.entity.Persona;


@Service
public class PersonaServiceImpl implements IPersonaService{
	@Autowired
	private IPersonaDao personaDao;
	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}
	@Override
	public Persona findById(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}
	@Override
	public Persona save(Persona persona) {
		// TODO Auto-generated method stub
		return personaDao.save(persona);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personaDao.deleteById(id);
	}

}
