package org.itboys.portal.entity;

import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * 信息关联对象管理
 * @author Administrator
 *
 */
@Entity(value = "InfoObjRel", noClassnameStored = true)
public class InfoObjRel implements Serializable{

	private static final long serialVersionUID = 67189668454096348L;
	@Id
	private Long id;
	private Long infoId;
	private Long objId;
	private Integer type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
