package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductCategoryCodeConfigMapper;
import org.itboys.product.entity.mysql.product.ProductCategoryCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品分类的默认特征值code的配置
 * @author huml
 */
@Service
public class ProductCategoryCodeConfigBO {
	

	@Autowired
	private ProductCategoryCodeConfigMapper productCategoryCodeConfigMapper;
	
	public List<ProductCategoryCodeConfig> getListByCatid(Long catId){
			
		return productCategoryCodeConfigMapper.getListByCatid(catId);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductCategoryCodeConfig findById(Long id){
		return productCategoryCodeConfigMapper.findById(id);
	}
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCategoryCodeConfig entity){
		productCategoryCodeConfigMapper.insert(entity);
	}
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(ProductCategoryCodeConfig entity){
		productCategoryCodeConfigMapper.update(entity);
	}
	
	
	/**
	 * 删除
	 */
	public int delete(Long id){
		return productCategoryCodeConfigMapper.delete(id);
	}
	
	public List<ProductCategoryCodeConfig>  getAllList(){
		return productCategoryCodeConfigMapper.getAllList();
	}
	
	public  List<ProductCategoryCodeConfig> getListByCatidAndType(Long catId,Integer type){
		return productCategoryCodeConfigMapper.getListByCatidAndType(catId, type);
	}
	
}
