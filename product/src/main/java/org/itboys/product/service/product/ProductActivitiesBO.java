package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductActivitiesMapper;
import org.itboys.product.entity.mysql.product.ProductActivities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductActivitiesBO {
	@Autowired
	private ProductActivitiesMapper productActivitiesMapper;
	
	public List<ProductActivities> list(Map<String, Object> param){
		return productActivitiesMapper.list(param);
	}
	public Long count(Map<String, Object> param){
		return productActivitiesMapper.count(param);
	}
	
	public ProductActivities findById(Long id){
		return productActivitiesMapper.findById(id);
	}
	public List<ProductActivities> findByBrandId(Long brandId){
		return productActivitiesMapper.findByBrandId(brandId);
	}
	
	public void insert(ProductActivities productActivities){
		productActivitiesMapper.insert(productActivities);
	}
	public void update(ProductActivities productActivities){
		productActivitiesMapper.update(productActivities);
	}
	public void delete(Long id){
		productActivitiesMapper.delete(id);
	}
	public void deleteByBrandId(Long brandId){
		productActivitiesMapper.deleteByBrandId(brandId);
	}
}
