package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductTag;

/**
 * 商品标签
 * @author huml
 *
 */
public interface ProductTagMapper {
	
	
	public List<ProductTag> list(Map<String,Object> queryParam);
	
	/**
	 * @return
	 */
	public long count(Map<String,Object> sqlMap);
	
	/**
	 * 插入
	 * @param adminModule
	 */
	public void insert(ProductTag entity);
	
	/**
	 * 更新
	 * @param adminModule
	 */
	public int update(ProductTag entity);
	
	/**
	 * 删除
	 * @param id
	 */
	public int delete(Long id);

}
