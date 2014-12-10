package org.itboys.portal.entity;


import com.google.code.morphia.annotations.Entity;


/**
 * 项目portal导航布局关联的元素
 * 
 * @author huml
 * 
 */
@Entity(value = "PortalAppElement", noClassnameStored = true)
public class PortalAppElement extends PortalBaseEntity {

	private static final long serialVersionUID = 5656539325218128261L;
	private Long navId;
	private Long elementId;
	private String json;

	public Long getNavId() {
		return navId;
	}

	public void setNavId(Long navId) {
		this.navId = navId;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
