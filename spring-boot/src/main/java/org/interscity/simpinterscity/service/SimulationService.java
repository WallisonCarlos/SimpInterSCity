package org.interscity.simpinterscity.service;

import static java.time.ZonedDateTime.now;
import static org.interscity.simpinterscity.util.file.FileManager.FILE_SEPARATOR;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.interscity.simpinterscity.dto.KeyDTO;
import org.interscity.simpinterscity.dto.SimulationDTO;
import org.interscity.simpinterscity.model.Scenario;
import org.interscity.simpinterscity.model.Simulation;
import org.interscity.simpinterscity.model.enumeration.AddFileTypeEnum;
import org.interscity.simpinterscity.repository.ScenarioRepository;
import org.interscity.simpinterscity.repository.SimulationRepository;
import org.interscity.simpinterscity.util.file.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.drive.Drive.Comments.Update;

@Service
public class SimulationService {

	@Autowired
	private SimulationRepository simulationRepository;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private RunnerShellScript runnerShellScript;
	@Value("${interscsimulator.dir}")
	private String interSCSimulatorDir;
	@Value("${interscsimulator.dir.smart.city.model}")
	private String smartCityModel;

	public Simulation findBy(String id) {
		Optional<Simulation> entity = simulationRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	public List<Simulation> findAll() {
		return simulationRepository.findAll();
	}

	public Simulation create(SimulationDTO entity) {
		Simulation simulation = new Simulation();
		simulation.setTitle(entity.getTitle());
		simulation.setDescription(entity.getDescription());
		Scenario scenario = scenarioService.findBy(entity.getScenario());
		simulation.setScenario(scenario);
		entity.setCreated(now());
		return simulationRepository.save(entity);
	}

	public Simulation run(KeyDTO key) {
		Simulation simulation = findBy(key.getKey());
		String scenarioPath = smartCityModel.concat(File.separator).concat(simulation.getId());
		FileManager.createDir(new File(scenarioPath));
		copyScenarioFiles(scenarioPath, simulation.getScenario());
		createConfigFile(simulation, scenarioPath);
		simulation.setBuildStart(now());
		runnerShellScript.runner(interSCSimulatorDir.concat(File.separator).concat("build.sh"));
		simulation.setBuildFinished(now());
		simulation.setStarted(now());
		runnerShellScript.runner(interSCSimulatorDir.concat(File.separator).concat("run.sh"), simulation.getId());
		simulation.setFinished(now());
		return update(simulation);
	}

	private void createConfigFile(Simulation simulation, String scenarioPath) {
		StringBuilder content = new StringBuilder("<scsimulator_config>");
		content.append("\t<config").append("\t\ttrip_file=\"../{{scenario}}/trips.xml\"")
				.append("\t\tmap_file=\"../{{scenario}}/map.xml\"")
				.append("\t\toutput_file=\"../{{scenario}}/output-{{scenario}}.xml\"")
				.append("\t\ttraffic_signals_file=\"../{{scenario}}/signals.xml\"")
				.append("\t\tdigital_rails_file=\"../{{scenario}}/empty-digital-rails.xml\"")
				.append("\t\tsimulation_time=\"" + simulation.getScenario().getSimulationTime() + "\"/>")
				.append("</scsimulator_config>");
		FileManager.create(new File(scenarioPath.concat(File.separator).concat("config.xml")),
				content.toString().replace("{{scenario}}", simulation.getId()));

	}

	private void copyScenarioFiles(String scenarioPath, Scenario scenario) {
		if (scenario.getTripsFile() != null) {
			FileManager.copy(new File(scenario.getTripsFile()),
					new File(scenarioPath.concat(File.separator).concat("trips.xml")));
		}
		if (scenario.getMapFile() != null) {
			FileManager.copy(new File(scenario.getMapFile()),
					new File(scenarioPath.concat(File.separator).concat("map.xml")));
		}
		if (scenario.getBusFile() != null) {
			FileManager.copy(new File(scenario.getBusFile()),
					new File(scenarioPath.concat(File.separator).concat("buses.xml")));
		}
		if (scenario.getMetroFile() != null) {
			FileManager.copy(new File(scenario.getMetroFile()),
					new File(scenarioPath.concat(File.separator).concat("metro.xml")));
		}
		if (scenario.getDigitalRailsFile() != null) {
			FileManager.copy(new File(scenario.getDigitalRailsFile()),
					new File(scenarioPath.concat(File.separator).concat("digital-rails.xml")));
		}
		if (scenario.getEmptyDigitalRailsFile() != null) {
			FileManager.copy(new File(scenario.getEmptyDigitalRailsFile()),
					new File(scenarioPath.concat(File.separator).concat("empty-digital-rails.xml")));
		}
		if (scenario.getSignals() != null) {
			FileManager.copy(new File(scenario.getSignals()),
					new File(scenarioPath.concat(File.separator).concat("signals.xml")));
		}
	}

	public Simulation update(Simulation entity) {
		entity.setUpdated(now());
		return simulationRepository.save(entity);
	}

	public ResponseEntity<?> delete(String id) {
		simulationRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
