package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品字段扩展表 纵表
 * @author huml
 * 
 */
public class ProductExt extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	private Long productId;
	private String key;  //扩展字段的key
	private String value;  //扩展字段的value
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
