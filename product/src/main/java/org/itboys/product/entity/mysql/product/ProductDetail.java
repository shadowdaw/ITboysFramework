package org.itboys.product.entity.mysql.product;

import java.math.BigDecimal;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品特征值 比如颜色 尺码等详情
 * @author huml
 * 
 */
public class ProductDetail extends BaseEntity {
	
	private static final long serialVersionUID = 2482584465454652854L;
	
	private Long productId;
	private Long detailCodeId; //特征值code的ID
	private String value;  //特征值的值 比如 红色 绿色(picAddress) 或 L码 M码等
	private BigDecimal price;  //如果商品特征值的code配置上影响报价的话 那么这个值是改变报价的值
	private String img;  //如果特征值以图片展示的话 这个为特征值小图片的地址
	private Long stockSize;	//库存量
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getDetailCodeId() {
		return detailCodeId;
	}
	public void setDetailCodeId(Long detailCodeId) {
		this.detailCodeId = detailCodeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getStockSize() {
		return stockSize;
	}
	public void setStockSize(Long stockSize) {
		this.stockSize = stockSize;
	}
	  
	
	
	
}
