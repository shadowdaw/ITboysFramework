package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品标签实体类
 * huml
 */
public class ProductTag extends BaseEntity {

	private static final long serialVersionUID = -4410569155462989583L;
	private String name;// 标签名称
	private Long catId;// 关联分类id,不需要默认为0
	private Long objId;// 关联对象id,不需要默认为0
	private Integer objType;// 关联类型 待定
	private String fied1;// 预留字段
	private String fied2;// 预留字段
	private String desc;// 描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getFied1() {
		return fied1;
	}

	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}

	public String getFied2() {
		return fied2;
	}

	public void setFied2(String fied2) {
		this.fied2 = fied2;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
