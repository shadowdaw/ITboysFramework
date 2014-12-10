package org.itboys.portal.entity;

import java.util.Date;

import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;

public class PortalBaseEntity extends BaseAdminEntity{

	private static final long serialVersionUID = -4900153719018089913L;
	private Date createTime;
	private Date updateTime;
	private Long creator;
	private Long updater;
	private Integer isDeleted=0;
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
	}
}
