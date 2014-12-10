package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductTagRel;

public interface ProductTagRelMapper {

	/**
	 * 插入
	 * @param 
	 */
	public void insert(ProductTagRel entity);
	
	
	/**
	 * 批量插入
	 */
	public void batchInsert(List<ProductTagRel> list);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public int delete(Long productId);
	
	public List<ProductTagRel> list(Map<String, Object> sqlMap);
	
	
	public List<Long> selectProIds(Map<String, Object> sqlMap);

}
