package com.karaxtecnologia.porfolio.models.services;

import com.karaxtecnologia.porfolio.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
