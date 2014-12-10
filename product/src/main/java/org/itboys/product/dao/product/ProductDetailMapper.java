package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductDetail;

/**
 * 商品特征值 比如颜色 尺码等详情
 * @author huml
 *
 */
public interface ProductDetailMapper {

	
	public List<ProductDetail> getListByProductid(Long productId);
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductDetail> list);
	
	public void insert(ProductDetail entity);
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
	public List<ProductDetail>getAll();
	
	public List<ProductDetail>catList(List<Long>list);
	
	public ProductDetail getById(Long id);
	/**
	 * update
	 * @param entity
	 */
	public int update(ProductDetail entity);
	
	public List<ProductDetail> getList(Map<String, Object> sqlMap);
}
