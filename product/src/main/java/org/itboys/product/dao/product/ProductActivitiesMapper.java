package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductActivities;

public interface ProductActivitiesMapper {
	public List<ProductActivities> list(Map<String, Object> param);
	public Long count(Map<String, Object> param);
	
	public ProductActivities findById(Long id);
	public List<ProductActivities> findByBrandId(Long brandId);
	
	public void insert(ProductActivities productActivities);
	public void update(ProductActivities productActivities);
	public void delete(Long id);
	public void deleteByBrandId(Long brandId);
}
