package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductCategoryConfigMapper;
import org.itboys.product.entity.mysql.product.ProductCategoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品分类的默认特征值code的配置
 * @author huml
 */
@Service
public class ProductCategoryConfigBO {
	

	@Autowired
	private ProductCategoryConfigMapper productCategoryConfigMapper;
	
	public List<ProductCategoryConfig> getListByCodeConfigId(Long catId){
		return productCategoryConfigMapper.getListByCodeConfigId(catId);
	}
	
	public List<ProductCategoryConfig> getListByIds(List<Long>ids){
		return productCategoryConfigMapper.getListByIds(ids);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductCategoryConfig findById(Long id){
		return productCategoryConfigMapper.findById(id);
	}
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCategoryConfig entity){
		productCategoryConfigMapper.insert(entity);
	}
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(ProductCategoryConfig entity){
		productCategoryConfigMapper.update(entity);
	}
	
	
	/**
	 * 删除
	 */
	public int delete(Long id){
		return productCategoryConfigMapper.delete(id);
	}
	
}
