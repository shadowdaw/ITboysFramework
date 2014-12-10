package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品标签关联实体类
 * @author huml
 *
 */
public class ProductTagRel extends BaseEntity {

	private static final long serialVersionUID = 3223698171074103645L;
	private Long proTagId;// 商品标签id
	private Long productId;// 商品id
	private Long objId;// 关联对象id,不需要默认为0
	private String fied1;// 预留字段

	public Long getProTagId() {
		return proTagId;
	}

	public void setProTagId(Long proTagId) {
		this.proTagId = proTagId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public String getFied1() {
		return fied1;
	}

	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}

}
