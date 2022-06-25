package com.bolsaideas.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger logger = LogManager.getLogger(UploadFileServiceImpl.class);

	private static final String UPLOAD = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {

		Path pathFoto = getPath(filename);

		Resource recurso = null;

		recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		}

		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {

		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);

		logger.info("rootPath: " + rootPath);

		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;

	}

	@Override
	public boolean delete(String filename) {

		Path rootPath = getPath(filename);

		try {

			if (Files.deleteIfExists(rootPath)) {
				return true;
			}

		} catch (IOException io) {
			logger.error(io.getMessage());
		}

		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOAD).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {

		FileSystemUtils.deleteRecursively(Paths.get(UPLOAD).toFile());

	}

	@Override
	public void init() throws IOException {

		Files.createDirectories(Paths.get(UPLOAD));

	}

}
