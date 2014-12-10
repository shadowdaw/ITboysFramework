package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品分类的默认参数code的配置  商品分类的默认特征值详情的配置
 * @author huml
 * 
 */
public class ProductParamConfig extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	private String name;
	private Long catId;
	private Integer type;	//
	private Integer needUnit;
	private String desc;
	private String unit;
	private String extendField;
	
	public static Integer TYPE1= 1;//文本框
	public static Integer TYPE2= 2;//单选框
	public static Integer TYPE3= 3;//多选框
	
	
	public static Integer NEEDUNIT_YES = 1;//需要
	public static Integer NEEDUNIT_NO = 2;//不需要
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNeedUnit() {
		return needUnit;
	}
	public void setNeedUnit(Integer needUnit) {
		this.needUnit = needUnit;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getExtendField() {
		return extendField;
	}
	public void setExtendField(String extendField) {
		this.extendField = extendField;
	}
	
	
	
	
	
}
