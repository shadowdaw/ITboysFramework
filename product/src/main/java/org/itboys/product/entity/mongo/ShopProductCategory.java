package org.itboys.product.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 点偶的产品分类
 * @author ok
 *
 */
@Entity(value = "shopProductCategory", noClassnameStored = true)
public class ShopProductCategory extends BaseLongIdEntity {

	private static final long serialVersionUID = -46382364251346L;
	
	private Long shopId;
	private Long parentId;
	private String name;
	private String desc;
	private Integer level;
	private Integer sort;//排序
	private String fullPathId;
	private Long creator;
	private Long updater;
	private Integer isDeleted=0;
	private Integer isBottom;//1:是    2:否
	private String image;
	private Date createTime;
	private Date updateTime;
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
	
	public Long get_parentId(){
		return this.parentId;
	}
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getFullPathId() {
		return fullPathId;
	}
	public void setFullPathId(String fullPathId) {
		this.fullPathId = fullPathId;
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
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getIsBottom() {
		return isBottom;
	}
	public void setIsBottom(Integer isBottom) {
		this.isBottom = isBottom;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
