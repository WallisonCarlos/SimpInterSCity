package org.interscity.simpinterscity.resource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.interscity.simpinterscity.model.Scenario;
import org.interscity.simpinterscity.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/scenarios"})
public class ScenarioResource {
	
	@Autowired
	private ScenarioService scenarioService;
	
	@GetMapping
	public List<Scenario> index() {
		return scenarioService.findAll();
	}
	
	@GetMapping("/{id}")
	public Scenario show(@PathVariable("id") String id) {
		return scenarioService.findBy(id);
	}
	
	@PostMapping
	public Scenario create(@RequestBody Scenario entity) {
		return scenarioService.create(entity);
	}
	
	@PostMapping("/add-file/{type}/{id}")
	public Scenario create(@PathVariable("type") String type, @PathVariable("id") String id, @RequestParam MultipartFile file) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		return scenarioService.addFile(id, type, file);
	}
	
	@PostMapping("/generate/trips/{id}")
	public Scenario generateTrips(@PathVariable("id") String id) {
		return scenarioService.generateTrips(id);
	}
	
	@PutMapping
	public Scenario update(@RequestBody Scenario entity) {
		return scenarioService.update(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return scenarioService.delete(id);
	}

}
