package org.itboys.product.dao.product;

import java.util.List;

import org.itboys.product.entity.mysql.product.ProductKeyword;

/**
 * 产品关键字
 * @author huml
 *
 */
public interface ProductKeywordMapper {

	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public List<ProductKeyword> findByProductId(Long productId);
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductKeyword> list);
	
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
}
