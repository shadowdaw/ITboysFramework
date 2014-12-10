package org.itboys.admin.entity;

import com.google.code.morphia.annotations.Entity;
/**
 * 组织实体
 * @author WeiSky
 *
 */
@Entity(value="AdminOrg", noClassnameStored = true)
public class AdminOrg extends BaseAdminEntity{

	private static final long serialVersionUID = -5412184466600986827L;

	private String name;//组织名称
	
	private long parentId;//上级组织ID
	
	private String fullPath;//组织全路径
	
	private String fullNamePath;//组织名称全路径
	
	private Integer level;//层级
	
	private Integer sort;//排序字段
	
	private String desc;//组织描述
	
	private Integer isDeleted = 0;//逻辑删除标记 0有效 1删除

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
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

	public void setFullNamePath(String fullNamePath) {
		this.fullNamePath = fullNamePath;
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
