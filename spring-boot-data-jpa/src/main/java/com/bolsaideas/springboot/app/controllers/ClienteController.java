package com.bolsaideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsaideas.springboot.app.models.entity.Cliente;
import com.bolsaideas.springboot.app.models.service.IClienteService;
import com.bolsaideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	private final Logger logger = LogManager.getLogger(ClienteController.class);

	private static final String REDIRECT_LISTAR = "redirect:/listar";
	private static final String CLIENTE = "cliente";
	private static final String ERROR = "error";
	private static final String TITULO = "titulo";

	@Autowired
	@Qualifier("clienteServiceImpl")
	private IClienteService clienteService;

	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			
			recurso = new UrlResource(pathFoto.toUri());
			
			if(!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
			}
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);	
	}
	
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);

		if (cliente == null) {
			flash.addFlashAttribute(ERROR, "El cliente no existe en la base de datos");
			return REDIRECT_LISTAR;
		}

		model.addAttribute(CLIENTE, cliente);
		model.addAttribute(TITULO, "Detalle cliente: " + cliente.getNombre());

		return "ver";
	}

	@GetMapping(value = "/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute(TITULO, "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put(CLIENTE, cliente);
		model.put(TITULO, "Formulario de Cliente");

		return "form";
	}

	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);

			if (cliente == null) {
				flash.addFlashAttribute(ERROR, "El id del cliente no existe en la BBDD!");
				return REDIRECT_LISTAR;
			}
		} else {
			flash.addFlashAttribute(ERROR, "El id del cliente no puede ser 0!");
			return "redirect:listar";
		}

		model.addAttribute(CLIENTE, cliente);
		model.addAttribute(TITULO, "Editar cliente");

		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute(CLIENTE, cliente);
			model.addAttribute(TITULO, "Formulario de Cliente");

			return "form";
		}

		if (!foto.isEmpty()) {

			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

			Path rootAbsolutPath = rootPath.toAbsolutePath();
			
			logger.info("rootPath: " + rootPath);

			try {

				Files.copy(foto.getInputStream(), rootAbsolutPath);

				flash.addAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

				cliente.setFoto(uniqueFilename);

			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:listar";
	}

	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			clienteService.delete(id);
		}

		flash.addFlashAttribute("success", "Cliente eliminado con éxito!");

		return REDIRECT_LISTAR;
	}

}
