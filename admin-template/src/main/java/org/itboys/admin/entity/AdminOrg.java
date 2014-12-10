package org.itboys.admin.entity;

import java.io.UnsupportedEncodingException;

import com.google.code.morphia.annotations.Entity;
/**
 * 组织实体
 * @author WeiSky
 *
 */
@Entity(value="AdminOrg", noClassnameStored = true)
public class AdminOrg extends BaseAdminEntity{

	private static final long serialVersionUID = -8154137735838895198L;

	private String name;//组织名称
	
	private Long parentId;//上级组织ID
	
	private String fullPath;//组织全路径
	
	private String fullNamePath;//组织名称全路径
	
	private Integer level;//层级
	
	private Integer sort;//排序字段
	
	private String desc;//组织描述
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除
	
	private Integer isArea;
	
	private Integer provinceId;//省
	private Integer districtId;//地区
	private Integer cityId;//市 县 区

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getFullNamePath() {
		return fullNamePath;
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

	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFullNamePath(String fullNamePath) {
		this.fullNamePath = fullNamePath;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Long get_parentId(){
		return this.parentId;
	}
	
	public Integer getIsArea() {
		return isArea;
	}

	public void setIsArea(Integer isArea) {
		this.isArea = isArea;
	}
	
}
