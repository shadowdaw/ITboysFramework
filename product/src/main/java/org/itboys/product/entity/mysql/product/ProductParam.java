package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品参数值 比如颜色 尺码等详情
 * @author huml
 * 
 */
public class ProductParam extends BaseEntity {
	
	private static final long serialVersionUID = 797395233018245016L;
	
	private Long productId;
	private Long paramConfigId;
	private String value;
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getParamConfigId() {
		return paramConfigId;
	}
	public void setParamConfigId(Long paramConfigId) {
		this.paramConfigId = paramConfigId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
	
}
