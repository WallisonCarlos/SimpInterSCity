package org.interscity.simpinterscity.service;

import static java.time.ZonedDateTime.now;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.interscity.simpinterscity.dto.AddIntervalDTO;
import org.interscity.simpinterscity.dto.KeyDTO;
import org.interscity.simpinterscity.dto.SimulationPointDTO;
import org.interscity.simpinterscity.model.Simulation;
import org.interscity.simpinterscity.model.SimulationPoint;
import org.interscity.simpinterscity.model.enumeration.GeneratorIntervalsEnum;
import org.interscity.simpinterscity.repository.SimulationPointRepository;
import org.interscity.simpinterscity.util.FlowAccessToLinkPerTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public class SimulationPointService {
    
    @Autowired
	private SimulationPointRepository simulationPointRepository;
    @Autowired
    private SimulationService simulationService;
    @Value("${interscsimulator.dir}")
	private String interSCSimulatorDir;
	
	public SimulationPoint findBy(String id) {
		Optional<SimulationPoint> entity  = simulationPointRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}
	
	public List<SimulationPoint> findAll() {
		return simulationPointRepository.findAll();
	}
	
	public SimulationPoint create(SimulationPointDTO entity) {
        SimulationPoint simulationPoint = new SimulationPoint(entity.getTitle(), 
        entity.getDescription(), entity.getAuthor(), 
        entity.getNumberOfClusters(), entity.getPercentageOfTrips());
        simulationPoint.setBase(simulationService.findBy(entity.getBase()));
        simulationPoint.setOther(simulationService.findBy(entity.getOther()));
		simulationPoint.setCreated(now());
		return simulationPointRepository.save(simulationPoint);
	}

    public SimulationPoint addIntervals(AddIntervalDTO entity) {
        SimulationPoint simulationPoint = findBy(entity.getKey());
        Integer simulationTime = simulationPoint.getBase().getScenario().getSimulationTime();
        simulationPoint.setIntervals(GeneratorIntervalsEnum.instance().generator(simulationTime, entity);
        return update(simulationPoint);
    }

    public ResponseEntity<?> generateFlowPerTime(KeyDTO key) throws IOException {
        Simulation simulation = simulationService.findBy(key.getKey());
        String outputInterSCSimulatorFile = "output/output-".concat(simulation.getId()).concat(".csv");
        FlowAccessToLinkPerTimeUtil.generate(new File(interSCSimulatorDir.concat(File.separator).concat(outputInterSCSimulatorFile)), ";", simulation.getId(), true);
        return ResponseEntity.ok().build();
    }
	
	public SimulationPoint update(SimulationPoint entity) {
		entity.setUpdated(now());
		return simulationPointRepository.save(entity);
	}
	
	public ResponseEntity<?> delete(String id) {
		simulationPointRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
    
}
