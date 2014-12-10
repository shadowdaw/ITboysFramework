package org.itboys.product.dto.product;

import org.itboys.product.entity.mysql.product.ProductBrand;

/**
 * 
 * @author huml
 *
 */
public class ProductBrandDTO  extends ProductBrand{
	
	private static final long serialVersionUID = 3166966598760744588L;
	
	private  Long brandId;
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	
	
	
	
	
}
