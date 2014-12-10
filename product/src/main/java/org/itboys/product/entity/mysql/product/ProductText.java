package org.itboys.product.entity.mysql.product;

import java.io.Serializable;

/**
 * 商品大字段表
 * @author huml
 * 
 */
public class ProductText implements Serializable {

	
	private static final long serialVersionUID = -4993389991767131967L;
	private Long productId;
	private String text;  //
	private String text1;  //
	private String text2;  //
	private String text3;  //
	private String text4;  //
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	
}
