package org.itboys.product.dao.product;

import org.itboys.product.entity.mysql.product.ProductText;

/**
 * 商品大字段
 * @author huml
 *
 */
public interface ProductTextMapper {

	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public ProductText findByProductId(Long productId);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductText entity);
	
	
	/**
	 * 修改
	 * @param productId
	 * @return
	 */
	public int update(ProductText entity);
	
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
}
