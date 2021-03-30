package org.interscity.simpinterscity.model.enumeration;

public enum AddFileTypeEnum {
	
	TRIPS("trips", "setTripsFile"),
	MAP("map", "setMapFile"),
	METRO("metro", "setMetroFile"),
	BUS("bus", "setBusFile"),
	SIGNAL("signals", "setSignals"),
	EMPTY_DIGITAL_RAILS("empty-digital-rails", "setEmptyDigitalRailsFile"),
	DIGITAL_RAILS("digital-rails", "setDigitalRailsFile");
	
	private String type;
	private String method;
	
	AddFileTypeEnum(String type, String method) {
		this.type = type;
		this.method = method;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMethod() {
		return method;
	}

	public static AddFileTypeEnum valueOfType(String type) {
		for (AddFileTypeEnum fileType : AddFileTypeEnum.values()) {
			if (fileType.getType().equals(type)) {
				return fileType;
			}
		}
		return null;
	}
	
}
