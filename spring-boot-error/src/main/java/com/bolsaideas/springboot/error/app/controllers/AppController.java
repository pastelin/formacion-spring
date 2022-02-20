package com.bolsaideas.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsaideas.springboot.error.app.domain.Usuario;
import com.bolsaideas.springboot.error.app.errors.UsuarioNoEncontradoException;
import com.bolsaideas.springboot.error.app.services.UsuarioService;

@Controller
public class AppController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/index")
	public String index() {

		int j = 100 / 0;
		
		return "index";
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id, Model model) {

//		Usuario usuario = usuarioService.obtenerPorId(id);

//		if (usuario == null) {
//			throw new UsuarioNoEncontradoException(id.toString());
//		}

		Usuario usuario = usuarioService.obtenerPorIdOptional(id).orElseThrow( () -> new UsuarioNoEncontradoException(id.toString()) );
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Detalle usaurio: ".concat(usuario.getNombre()));

		return "ver";
	}

}
