package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "AdminMenu", noClassnameStored = true)
public class AdminMenu extends BaseAdminEntity{

	private static final long serialVersionUID = 2789900884744498912L;

	private String menuName;//菜单名称
	
	private String url;//菜单连接地址
	
	private String fullPath;//菜单全路径
	
	private long parentId;//父菜单ID 一级菜单该值为0
	
	private Integer level;//层级
	
	private Integer sort;//排序字段
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
