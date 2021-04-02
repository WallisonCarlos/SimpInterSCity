package org.interscity.simpinterscity.resource;

import java.util.List;

import org.interscity.simpinterscity.dto.AddIntervalDTO;
import org.interscity.simpinterscity.dto.SimulationPointDTO;
import org.interscity.simpinterscity.model.SimulationPoint;
import org.interscity.simpinterscity.service.SimulationPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SimulationPointResource {

    @Autowired
    private SimulationPointService simulationPointService;
	
	@GetMapping
	public List<SimulationPoint> index() {
		return simulationPointService.findAll();
	}
	
	@GetMapping("/{id}")
	public SimulationPoint show(@PathVariable("id") String id) {
		return simulationPointService.findBy(id);
	}
	
	@PostMapping
	public SimulationPoint create(@RequestBody SimulationPointDTO entity) {
		return simulationPointService.create(entity);
	}

    @PostMapping("/add/intervals")
	public SimulationPoint addIntervals(@RequestBody AddIntervalDTO entity) {
		return simulationPointService.addIntervals(entity);
	}
	
	@PutMapping
	public SimulationPoint update(@RequestBody SimulationPoint entity) {
		return simulationPointService.update(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return simulationPointService.delete(id);
	}
    
}
