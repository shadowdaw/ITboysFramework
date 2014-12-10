package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品特征值的配置code 就是商品有哪些主特征 比如 颜色 尺码 品种 等
 * @author Liuguanjun
 * 2013-06-08
 */
public class ProductDetailCode extends BaseEntity {
	
	private static final long serialVersionUID = 2482584465454652854L;
	
	private Long productId;
	private Long categoryCodeConfigId; //特征值的值 比如 尺码 颜色
	private Integer isChangePrice;  //该特征值是否可以改变报价 一个商品上最多只有一个特征配置可以改变保健
	private Integer isLinkStock;	//是否关联库存
	
	public static Integer CHANGEPRICE_NO =1;//
	public static Integer CHANGEPRICE_YES =2;//
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCategoryCodeConfigId() {
		return categoryCodeConfigId;
	}
	public void setCategoryCodeConfigId(Long categoryCodeConfigId) {
		this.categoryCodeConfigId = categoryCodeConfigId;
	}
	public Integer getIsChangePrice() {
		return isChangePrice;
	}
	public void setIsChangePrice(Integer isChangePrice) {
		this.isChangePrice = isChangePrice;
	}
	public Integer getIsLinkStock() {
		return isLinkStock;
	}
	public void setIsLinkStock(Integer isLinkStock) {
		this.isLinkStock = isLinkStock;
	}
}
