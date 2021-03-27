package org.interscity.simpinterscity.model.scenario;

public abstract class Element {
	
	private ElementTypeEnum typeElement;
	
	public abstract Node getNode(Map map);

	public ElementTypeEnum getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(ElementTypeEnum typeElement) {
		this.typeElement = typeElement;
	}

	
	
	

}
