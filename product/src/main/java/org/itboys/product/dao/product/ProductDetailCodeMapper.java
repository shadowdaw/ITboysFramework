package org.itboys.product.dao.product;

import java.util.List;

import org.itboys.product.entity.mysql.product.ProductDetailCode;

/**
 * 商品特征值的配置code 就是商品有哪些主特征 比如 颜色 尺码 品种 等
 * @author huml
 *
 */
public interface ProductDetailCodeMapper {

	
	public List<ProductDetailCode> getListByProductid(Long productId);
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductDetailCode> list);
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
	public void insert(ProductDetailCode entity);
}
