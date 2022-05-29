package com.karaxtecnologia.porfolio.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.karaxtecnologia.porfolio.models.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona,Long>{

}
