package org.itboys.product.entity.mysql.product;

import java.util.List;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品分类的默认特征值code的配置
 * @author huml
 * 
 */
public class ProductCategoryCodeConfig extends BaseEntity {
	
	private static final long serialVersionUID = 2482584465454652854L;
	
	private Long catId;
	private String value;
	private Integer type;  //类型 1:颜色 2:文字
	
	public static Integer TYPE_COLRE =1;//颜色(图片类型的)
	public static Integer TYPE_WORD =2;
	
	
	List<ProductCategoryConfig> catconfigList;
	
	
	public List<ProductCategoryConfig> getCatconfigList() {
		return catconfigList;
	}
	public void setCatconfigList(List<ProductCategoryConfig> catconfigList) {
		this.catconfigList = catconfigList;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
}
