package org.itboys.product.entity.mysql.product;

import java.io.Serializable;

/**
 * 信息搜素关键词
 * @author huml
 *
 */
public class ProductKeyword implements Serializable {

	private static final long serialVersionUID = -5252874022797253780L;
	private Long id;
	private Long productId;
	private String keyword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
