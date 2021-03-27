package org.interscity.simpinterscity.model.scenario;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.interscity.simpinterscity.util.XMLConversor;

public class ModelFactory {
	
	public static String BASE_DIR = "scenarios/sp_completo";
	
	public static Map buildMap() throws JAXBException {
		File mapFile = new File(BASE_DIR+"/map.xml");
//		System.out.println("Building Map");
		Map map = null;
		if (mapFile.exists()) {
			map = new XMLConversor<Map>(mapFile)
					.xmlToObject(JAXBContext.newInstance(Map.class));
//			System.out.println("Complete Map");
		} else {
			System.err.println("Aquivo ou diretório: "+BASE_DIR+"/map.xml. Não foi encontrado");
		}
		return map;
	}
	
	public static SimulatorBuses buildSimulatorBuses() throws JAXBException {
		File busFile = new File(BASE_DIR+"/buses.xml");
		SimulatorBuses simulatorBuses = null;
		System.out.println("Building SimulatorBuses");
		if (busFile.exists()) {
			simulatorBuses = new XMLConversor<SimulatorBuses>(busFile)
					.xmlToObject(JAXBContext.newInstance(SimulatorBuses.class));
			System.out.println("Complete SimulatorBuses");
		} else {
			System.err.println("Aquivo ou diretório: "+BASE_DIR+"/buses.xml. Não foi encontrado");
		}
		return simulatorBuses;
	}

	
	
	public static Metro buildMetro() throws JAXBException {
		File file = new File(BASE_DIR+"/metro.xml");
		Metro metro = null;
		System.out.println("Building Metro");
		if (file.exists()) {
			metro = new XMLConversor<Metro>(file)
					.xmlToObject(JAXBContext.newInstance(Metro.class));
			System.out.println("Complete Metro");
		} else {
			System.err.println("Aquivo ou diretório: "+BASE_DIR+"/metro.xml. Não foi encontrado");
		}
		return metro;
	}
	
	public static SCSimulatorMatrix buildSCSimulatorMatrix() throws JAXBException {
		File file = new File(BASE_DIR+"/trips.xml");
		SCSimulatorMatrix scSimulatorMatrix = null;
//		System.out.println("Building SCSimulatorMatrix");
		if (file.exists()) {
			scSimulatorMatrix = new XMLConversor<SCSimulatorMatrix>(file)
					.xmlToObject(JAXBContext.newInstance(SCSimulatorMatrix.class));
//			System.out.println("Complete SCSimulatorMatrix");
		} else {
			System.err.println("Aquivo ou diretório: "+BASE_DIR+"/trips.xml. Não foi encontrado");
		}
		return scSimulatorMatrix;
	}

	public static SCSimulatorMatrix buildSCSimulatorMatrix(String tripsFile) throws JAXBException {
		File file = new File(BASE_DIR+"/"+tripsFile);
		SCSimulatorMatrix scSimulatorMatrix = null;
		if (file.exists()) {
			scSimulatorMatrix = new XMLConversor<SCSimulatorMatrix>(file)
					.xmlToObject(JAXBContext.newInstance(SCSimulatorMatrix.class));
		} else {
			System.err.println("Aquivo ou diretório: "+BASE_DIR+"/trips.xml. Não foi encontrado");
		}
		return scSimulatorMatrix;
	}

}
