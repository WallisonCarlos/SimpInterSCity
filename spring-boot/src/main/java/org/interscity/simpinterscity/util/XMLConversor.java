package org.interscity.simpinterscity.util;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;  

public class XMLConversor<T> {
	
	private File file;
	private T object;
	
	public XMLConversor(File file) {
		this.file = file;
	}
	
	public XMLConversor(T object, File file) {
		this.file = file;
		this.object = object;
	}
	
	public void createFile() {
		try {
			file = objetctToXml();
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
	    }
	}
	
	public File objetctToXml() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(object, file);
	        marshaller.marshal(object, System.out);
	        return file;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T xmlToObject(JAXBContext jaxbContext) {
		try {
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        object = (T) unmarshaller.unmarshal(file);
	        return object;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	

}
