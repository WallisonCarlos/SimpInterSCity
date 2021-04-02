package org.interscity.simpinterscity.resource;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.interscity.simpinterscity.dto.KeyDTO;
import org.interscity.simpinterscity.dto.SimulationDTO;
import org.interscity.simpinterscity.model.Simulation;
import org.interscity.simpinterscity.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping({"/simulations"})
public class SimulationResource {
	
	@Autowired
	private SimulationService simulationService;
	
	@GetMapping
	public List<Simulation> index() {
		return simulationService.findAll();
	}
	
	@GetMapping("/{id}")
	public Simulation show(@PathVariable("id") String id) {
		return simulationService.findBy(id);
	}
	
	@PostMapping
	public Simulation create(@RequestBody SimulationDTO entity) {
		return simulationService.create(entity);
	}
	
	@PostMapping("/run")
	public Simulation run(@RequestBody KeyDTO entity) throws IOException, InterruptedException {
		return simulationService.run(entity);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/run", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent> runWebFlux() {
		return simulationService.getFluxProcessor().map(e -> ServerSentEvent.builder(e).build());
	}

	@PostMapping("/server-start")
	public ResponseEntity<?> startServer()  throws IOException {
		return simulationService.startServer();
	}
	
	@PutMapping
	public Simulation update(@RequestBody Simulation entity) {
		return simulationService.update(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return simulationService.delete(id);
	}

}
