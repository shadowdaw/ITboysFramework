package org.itboys.portal.dto;

import java.util.List;

import org.itboys.portal.entity.PortalInfo;
import org.itboys.portal.entity.PortalItem;

public class PortalItemDTO {
	private PortalItem portalItem;
	
	private List<PortalInfo> child;
	
	//非持久字段
	private List<PortalItem> children;

	public PortalItem getPortalItem() {
		return portalItem;
	}


	public void setPortalItem(PortalItem portalItem) {
		this.portalItem = portalItem;
	}


	public List<PortalItem> getChildren() {
		return children;
	}


	public void setChildren(List<PortalItem> children) {
		this.children = children;
	}


	public List<PortalInfo> getChild() {
		return child;
	}


	public void setChild(List<PortalInfo> child) {
		this.child = child;
	}


	private PortalInfo portalInfo;//用于单条信息的加载
	
	public PortalInfo getPortalInfo() {
		return portalInfo;
	}


	public void setPortalInfo(PortalInfo portalInfo) {
		this.portalInfo = portalInfo;
	}
}
