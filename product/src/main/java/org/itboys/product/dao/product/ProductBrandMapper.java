package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductBrand;

/**
 * 商品品牌
 * @author huml
 *
 */
public interface ProductBrandMapper {

	public List<ProductBrand> list(Map<String, Object> map);
	
	public List<ProductBrand> allList(Map<String, Object> map);
	
	public Long  count(Map<String, Object> map);
	
	public ProductBrand findById(Long id);
	
	public void insert(ProductBrand entity);
	
	public int update(ProductBrand entity);

	public int delete(Long id);
	
	public void deleteBySellerId(Long sellerId);
	
	public List<ProductBrand>getBySellerIdPcId(Map<String, Object> map);
}
