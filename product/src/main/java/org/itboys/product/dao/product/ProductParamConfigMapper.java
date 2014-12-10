package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dto.product.ParamValue;
import org.itboys.product.entity.mysql.product.ProductParamConfig;

/**
 * 商品分类的默认参数code的配置
 * @author huml
 *
 */
public interface ProductParamConfigMapper {

	
	public List<ProductParamConfig> getListByCatid(Long catId);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductParamConfig findById(Long id);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductParamConfig entity);
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(ProductParamConfig entity);
	
	
	/**
	 * 删除
	 */
	public int delete(Long id);
	
	/**
	 * 删除
	 */
	public int deleteByCatId(Long catId);
	
	/**************
	 * 根据id catId name 判断是否已经添加过
	 * @param map
	 * @return
	 */
	
	public List<ProductParamConfig> list(Map<String, Object> map);
	
	public long count(Map<String, Object> map);
	
	public List<ProductParamConfig>getListByProductId(Long productId);

	public List<ParamValue> findParamAndValue(Long productId);
	
}
