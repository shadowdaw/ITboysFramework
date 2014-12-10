package org.itboys.portal.entity;

import org.itboys.mongodb.entity.BaseEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * portal图片管理
 * @author huml
 *
 */
@Entity(value = "PortalInfoImage", noClassnameStored = true)
public class PortalInfoImage extends BaseLongIdEntity {
	
	private static final long serialVersionUID = -1088058558092225258L;
	private Long infoId;
	private String imgPath;  
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
}
