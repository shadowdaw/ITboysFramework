package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductImages;

/**
 * 商品大字段
 * @author huml
 *
 */
public interface ProductImagesMapper {

	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public List<ProductImages> findByProductId(Map<String, Object> map);
	
	/**
	 * 批量插入商品图片
	 * @param images
	 */
	public void batchInsert(List<ProductImages> list);
	
	
	/**
	 * 根据产品id 删除所有关联图片
	 */
	public int deleteByProductId(Long productId);
	
	/**
	 * 根据id 删除单个图片
	 */
	public int delete(Long id);
	
	public List<ProductImages> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
}
