package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品关联表
 * @author huml
 * 
 */
public class ProductObjRel extends BaseEntity {

	private static final long serialVersionUID = 6597783681992092749L;
	private Long id;
	private Long productId;
	private Long objId;
	private Integer type;
	
	public static Integer TYPE_PRODUCT = 1; //关联产品
	public static Integer TYPE_INFO = 2;//关联信息
	
	
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
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
