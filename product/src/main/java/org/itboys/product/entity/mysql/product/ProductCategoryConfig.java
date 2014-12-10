package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品分类的默认特征值详情的配置
 * @author huml
 * 
 */
public class ProductCategoryConfig extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	private Long codeConfigId;
	private String value;//颜色的图片活着尺码的值
	private String colorName;//颜色名称:尺码的话没有这个描述
	
	public Long getCodeConfigId() {
		return codeConfigId;
	}
	public void setCodeConfigId(Long codeConfigId) {
		this.codeConfigId = codeConfigId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
}