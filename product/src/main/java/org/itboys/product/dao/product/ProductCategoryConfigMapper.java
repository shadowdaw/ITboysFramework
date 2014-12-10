package org.itboys.product.dao.product;

import java.util.List;

import org.itboys.product.entity.mysql.product.ProductCategoryConfig;

/**
 * 商品分类的默认特征值code的配置
 * @author huml
 *
 */
public interface ProductCategoryConfigMapper {

	
	public List<ProductCategoryConfig> getListByCodeConfigId(Long catId);
	
	public List<ProductCategoryConfig> getListByIds(List<Long> ids);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductCategoryConfig findById(Long id);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCategoryConfig entity);
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(ProductCategoryConfig entity);
	
	
	/**
	 * 删除
	 */
	public int delete(Long id);
	
}
