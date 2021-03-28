package org.interscity.simpinterscity.model.enumeration;

public enum AddFileTypeEnum {
	
	TRIPS("trips", "setTripsFile");
	
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
