package org.itboys.product.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;
import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 店铺的图片信息
 * @author huml
 *
 */
@Entity(value = "shopImages", noClassnameStored = true)
public class ShopImages extends BaseLongIdEntity {

	private static final long serialVersionUID = 226528536533596L;
	
	private Long shopId;
	private String imgPath;
	private String imgDesc;
	private Date createTime;
	private Date updateTime;
	private Long creator;
	private Long updater;
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
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

}
