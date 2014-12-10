package org.itboys.portal.dto;

import java.util.List;

import org.itboys.portal.entity.Attachement;
import org.itboys.portal.entity.InfoCount;
import org.itboys.portal.entity.PortalInfo;

public class PortalInfoDTO  extends PortalInfo{
	private static final long serialVersionUID = -1944377345313918711L;
	private List<Attachement> images;//信息图片

	private List<Attachement> attachs;//信息附件
	
	private InfoCount infoCount; //信息浏览量
	
	public InfoCount getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(InfoCount infoCount) {
		this.infoCount = infoCount;
	}
	
	public List<Attachement> getImages() {
		return images;
	}

	public void setImages(List<Attachement> images) {
		this.images = images;
	}

	public List<Attachement> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Attachement> attachs) {
		this.attachs = attachs;
	}

}
