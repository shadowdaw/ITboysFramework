package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductDetails;


public interface ProductDetailsMapper {

	public List<ProductDetails> getListByProductid(Long productId);
	
	/**
	 * 批量插入
	 * @param list
	 */
	public void batchInsert(List<ProductDetails> list);
	
	public void insert(ProductDetails entity);
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
	public List<ProductDetails>getAll();
	
	public List<ProductDetails>catList(List<Long>list);
	
	public ProductDetails getById(Long id);
	/**
	 * update
	 * @param entity
	 */
	public int update(ProductDetails entity);
	
	public List<ProductDetails> getList(Map<String, Object> sqlMap);
}
