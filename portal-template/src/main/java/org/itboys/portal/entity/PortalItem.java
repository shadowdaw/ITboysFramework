package org.itboys.portal.entity;

import java.util.List;

import org.itboys.admin.entity.BaseAdminEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 公共栏目信息
 * @author huml
 *
 */
@Entity(value = "PortalItem", noClassnameStored = true)
public class PortalItem extends PortalBaseEntity{

	private static final long serialVersionUID = -3965467587149256438L;

	private Long parentId;
	private String name;
	private String code;
	private Integer type;
	private String image;
	private String desc;
	private Integer sort;
	private Integer isLeaf;
	private String url;
	
	public PortalItem(){}

	public Long get_parentId(){
		return this.parentId;
	}

	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}


	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
