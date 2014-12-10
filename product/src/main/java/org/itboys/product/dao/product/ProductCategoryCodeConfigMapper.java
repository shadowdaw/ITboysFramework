package org.itboys.product.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.product.ProductCategoryCodeConfig;

/**
 * 商品分类的默认特征值code的配置
 * @author huml
 *
 */
public interface ProductCategoryCodeConfigMapper {

	
	public List<ProductCategoryCodeConfig> getListByCatid(Long catId);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductCategoryCodeConfig findById(Long id);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCategoryCodeConfig entity);
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(ProductCategoryCodeConfig entity);
	
	
	/**
	 * 删除
	 */
	public int delete(Long id);
	
	public List<ProductCategoryCodeConfig>getAllList();
	
	public List<ProductCategoryCodeConfig> getListByCatidAndType(@Param("catId") Long catId,@Param("type") Integer type);
	
}
