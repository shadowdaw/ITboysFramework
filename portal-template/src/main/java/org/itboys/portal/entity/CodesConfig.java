package org.itboys.portal.entity;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "CodesConfig", noClassnameStored = true)
public class CodesConfig extends PortalBaseEntity {

	private static final long serialVersionUID = 5164001745551845895L;
	private String name;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
