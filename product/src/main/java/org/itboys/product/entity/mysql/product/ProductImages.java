package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品图片
 * @author huml
 * 
 */
public class ProductImages extends BaseEntity {
	
	private static final long serialVersionUID = -8905385531075279868L;
	
	private Long productId;
	private String imgPath;  //
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}