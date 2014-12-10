package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品品牌
 * @author huml
 * 
 */
public class ProductCategoryBrand extends BaseEntity {

	private static final long serialVersionUID = -5600980472192968223L;
	
	private  Long brandId;
	private  Long catId;
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	
	
	
	
	
}
