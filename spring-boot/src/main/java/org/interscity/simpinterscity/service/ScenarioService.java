package org.interscity.simpinterscity.service;

import static java.time.ZonedDateTime.now;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.interscity.simpinterscity.model.Scenario;
import org.interscity.simpinterscity.model.enumeration.AddFileTypeEnum;
import org.interscity.simpinterscity.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ScenarioService {
	
	@Autowired
	private ScenarioRepository scenarioRepository;
	@Autowired
	private FileService fileService;
	
	public Scenario findBy(String id) {
		Optional<Scenario> entity  = scenarioRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}
	
	public List<Scenario> findAll() {
		return scenarioRepository.findAll();
	}
	
	public Scenario create(Scenario entity) {
		entity.setCreated(now());
		return scenarioRepository.save(entity);
	}
	
	public Scenario addFile(String id, String type, MultipartFile file) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scenario scenario = findBy(id);
		String fileName = fileService.uploadFile(scenario, file);
		AddFileTypeEnum fileType = AddFileTypeEnum.valueOfType(type);
		Method method = Scenario.class.getMethod(fileType.getMethod(), String.class);
		method.invoke(scenario, fileName);
		return update(scenario);
	}
	
	public Scenario generateTrips(String id) {
		Scenario scenario = findBy(id);
		return scenario;
	}
	
	public Scenario update(Scenario entity) {
		entity.setUpdated(now());
		return scenarioRepository.save(entity);
	}
	
	public ResponseEntity<?> delete(String id) {
		scenarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
