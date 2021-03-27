package org.interscity.simpinterscity.model.scenario;

public enum ElementTypeEnum {
	
	BUS(1),
	STATION(2),
	TRIP(3),
	LINK_METRO(4),
	LINK(5),
	SIGNAL(6),
	NODE(7),
	METRO(8),
	PHASE(9),
	MAP(10);
	
	private int type;
	
	ElementTypeEnum(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	

}
