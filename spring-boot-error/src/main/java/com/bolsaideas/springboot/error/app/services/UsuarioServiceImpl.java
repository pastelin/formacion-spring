package com.bolsaideas.springboot.error.app.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.error.app.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;

	
	
	public UsuarioServiceImpl() {
		this.lista = Arrays.asList(
				new Usuario(1, "Juan", "Pastelin"),
				new Usuario(2, "Luis", "Fernandez"),
				new Usuario(3, "Paco", "Sanchez"),
				new Usuario(4, "Sara", "Ruiz"),
				new Usuario(5, "Lara", "Gomez"),
				new Usuario(6, "Memo", "Perez"),
				new Usuario(7, "Ulises", "Osnaya"));
	}

	@Override
	public List<Usuario> listar() {
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for(Usuario u: this.lista) {
			if(u.getId().equals(id) ) {
				resultado = u; 
			}
		}
		
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		
		Usuario usuario = this.obtenerPorId(id);
		
		return Optional.ofNullable(usuario);
	}

}
